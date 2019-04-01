package cn.edu.stu.customer.web.servlet;

import cn.edu.stu.customer.domain.Customer;
import cn.edu.stu.customer.service.CustomerService;
import cn.edu.stu.tools.CommonUtils;
import cn.edu.stu.tools.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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


    public String findAll(HttpServletRequest request,HttpServletResponse response)
            throws SQLException {

        //查询所有记录
        List<Customer> customerList=customerService.findAll();

        //将记录保存到request域中返回
        request.setAttribute("customerList",customerList);

        return "f:/list.jsp";
    }
}
