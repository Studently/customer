package cn.edu.stu.customer.web.servlet;

import cn.edu.stu.customer.domain.Customer;
import cn.edu.stu.customer.domain.PageBean;
import cn.edu.stu.customer.service.CustomerService;
import cn.edu.stu.tools.CommonUtils;
import cn.edu.stu.tools.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

/**
 * web层
 */
@WebServlet(name = "CustomerServlet",urlPatterns = "/CustomerServlet")
public class CustomerServlet extends BaseServlet {

    //依赖业务层
    private CustomerService customerService=new CustomerService();

    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        //把表单数据封装到用户信息中
        Customer customer= CommonUtils.toBean(request.getParameterMap(),Customer.class);
        System.out.println(customer.getCname());
        //设置用户id为uuid
        customer.setCid(CommonUtils.uuid());

        //添加到数据库
        customerService.add(customer);

        //将成功信息保存到request域中
        request.setAttribute("msg","恭喜，添加成功！");
        return "f:/msg.jsp";
    }


    /**
     * 查询用户所有信息
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String findAll(HttpServletRequest request,HttpServletResponse response)
            throws SQLException {

        //获取当前页
        int pc=getCurrentPage(request);
        int ps=10;//指定每页的大小
        //查询所有记录
        PageBean<Customer> pb=customerService.findAll(pc,ps);
        pb.setUrl(getUrl(request));

        //将分页对象保存到request域中返回
        request.setAttribute("pb",pb);

        return "f:/list.jsp";
    }


    /**
     * 编辑之前加载用户信息
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String preEdit(HttpServletRequest request,HttpServletResponse response) throws SQLException {

        //获取用户cid
        String cid=request.getParameter("cid");

        //根据用户cid加载用户信息
        Customer customer=customerService.load(cid);

        //保存用户信息到request域
        request.setAttribute("customer",customer);
        return "f:/edit.jsp";
    }


    /**
     * 编辑用户信息
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String edit(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        //封装表单数据到用户对象
        Customer customer=CommonUtils.toBean(request.getParameterMap(),Customer.class);
        //更新数据库
        customerService.edit(customer);
        //修改成功，返回提示信息
        request.setAttribute("msg","恭喜，用户信息修改成功！");
        return "f:/msg.jsp";
    }

    /**
     * 根据用户cid删除用户信息
     * @param request
     * @param response
     * @return
     */
    public String delete(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        //获取用户cid
        String cid=request.getParameter("cid");

        customerService.delete(cid);

        request.setAttribute("msg","恭喜，用户信息删除成功！");
        return "f:/msg.jsp";
    }

    /**
     * 多条件组合查询
     * @param request
     * @param response
     * @return
     */
    /*public String query(HttpServletRequest request,HttpServletResponse response) throws SQLException {

        //封装表单条件数据到用户对象中
        Customer criteria=CommonUtils.toBean(request.getParameterMap(),Customer.class);

        List<Customer> customerList=customerService.query(criteria);
        request.setAttribute("customerList",customerList);
        return "f:/list.jsp";
    }*/

    public String query(HttpServletRequest request,HttpServletResponse response) throws SQLException, UnsupportedEncodingException {

        //封装表单条件数据到用户对象中
        Customer criteria=CommonUtils.toBean(request.getParameterMap(),Customer.class);
        //编码
        //criteria=enCoding(criteria);

        //获取当前页
        int pc=getCurrentPage(request);
        int ps=10;//指定每页的大小

        PageBean<Customer> pb=customerService.query(criteria,pc,ps);
        pb.setUrl(getUrl(request));
        request.setAttribute("pb",pb);
        return "f:/list.jsp";
    }

    public int getCurrentPage(HttpServletRequest request){
        //获取当前请求页码
        String pc=request.getParameter("pc");
        //如果是第一次请求，没有传递页码，即请求第一页数据
        if(pc==null||pc.isEmpty()){
            return 1;
        }
        //否则，将页码有字符串转成int型
        return Integer.parseInt(pc);
    }

    /**
     * 获取请求url
     * @param request
     * @return
     */
    public String getUrl(HttpServletRequest request){
        String contextPath=request.getContextPath();//项目名
        String servletPath=request.getServletPath();//servlet名
        String queryPath=request.getQueryString();//参数名

        //如果参数中包含"pc="
        if(queryPath.contains("pc=")){
            int index=queryPath.indexOf("pc=");
            queryPath=queryPath.substring(0,index);//去除pc参数
        }
        return contextPath+servletPath+"?"+queryPath;
    }

    public Customer enCoding(Customer customer) throws UnsupportedEncodingException {
        String cname=customer.getCname();
        String gender=customer.getGender();
        String cellphone=customer.getCellphone();
        String email=customer.getEmail();

        if(cname!=null&&!cname.trim().isEmpty()){
            cname=new String(cname.getBytes("ISO-8859-1"),"utf-8");
            customer.setCname(cname);
        }

        if(gender!=null&&!gender.trim().isEmpty()){
            gender=new String(gender.getBytes("ISO-8859-1"),"utf-8");
            customer.setGender(gender);
        }

        if(cellphone!=null&&!cellphone.trim().isEmpty()){
            cellphone=new String(cellphone.getBytes("ISO-8859-1"),"utf-8");
            customer.setCellphone(cellphone);
        }

        if(email!=null&&!email.trim().isEmpty()){
            email=new String(email.getBytes("ISO-8859-1"),"utf-8");
            customer.setEmail(email);
        }

        return customer;
    }
}
