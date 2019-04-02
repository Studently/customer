package cn.edu.stu.customer.dao;

import cn.edu.stu.customer.domain.Customer;
import cn.edu.stu.customer.domain.PageBean;
import cn.edu.stu.tools.jdbc.TrQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * dao层，操作数据库
 */
public class CustomerDao {

    /**
     * dao层通过Utils.jar下的TrQueryRunner工具来操作数据库
     */
    private QueryRunner qr=new TrQueryRunner();

    /**
     * 添加用户信息
     * @param customer
     */
    public void add(Customer customer) throws SQLException {

        //创建sql模板
        String sql="insert into t_customer values(?,?,?,?,?,?,?)";

        //将用户信息传到参数中
        Object[] params={customer.getCid(),customer.getCname(),customer.getGender(),customer.getBirthday(),
        customer.getCellphone(),customer.getEmail(),customer.getDescription()};

        //执行插入
        qr.update(sql,params);
    }


    /**
     * 分页查询
     * @param pc 当前请求页
     * @param ps 每页大小
     * @return
     * @throws SQLException
     */
    public PageBean<Customer> findAll(int pc, int ps) throws SQLException {

        PageBean<Customer> pageBean=new PageBean<>();
        pageBean.setPc(pc);
        pageBean.setPs(ps);
        //获得所有记录数
        String sql="select count(*) from t_customer";
        //获得总记录数,类型是number
        Number num=(Number)qr.query(sql,new ScalarHandler());
        int tr= num.intValue();
        pageBean.setTr(tr);

        /**
         * 查询请求页需要的记录
         * limit 后面的两个参数含义：
         * ？：开始位置（从0开始，表示第一条记录）：(pc-1)*ps
         * ？：查询记录的条数 ps
         */
        sql="select * from t_customer limit ?,?";

        List<Customer> beanList=qr.query(sql,new BeanListHandler<>(Customer.class),(pc-1)*ps,ps);
        pageBean.setBeanList(beanList);

        //查询结果，返回pagebean
        return pageBean;
    }

    /**
     * 通过用户cid加载用户信息
     * @param cid
     * @return
     */
    public Customer load(String cid) throws SQLException {
        //创建sql模板
        String sql="select * from t_customer where cid=?";

        //查询结果，返回list
        return qr.query(sql, new BeanHandler<Customer>(Customer.class),cid);
    }

    /**
     * 编辑用户信息
     * @param customer
     */
    public void edit(Customer customer) throws SQLException {

        String sql="update t_customer set cname=?,gender=?,birthday=?,cellphone=?," +
                "email=?,description=? where cid=?";

        //将用户信息传到参数中
        Object[] params={customer.getCname(),customer.getGender(),customer.getBirthday(),
                customer.getCellphone(),customer.getEmail(),customer.getDescription(),customer.getCid()};

        qr.update(sql,params);
    }


    /**
     * 根据用户的cid删除用户信息
     * @param cid
     */
    public void delete(String cid) throws SQLException {

        String sql="delete from t_customer where cid=?";
        qr.update(sql,cid);
    }


    /**
     * 多条件组合查询
     * 4个条件：用户名、性别、手机行、邮箱
     * 其中除了性别以外，其他三个采用模糊查询
     * @param customer
     * @return
     */
    /*public List<Customer> query(Customer customer) throws SQLException {
        *//**
         * 1、创建sql模板
         *      这里考虑到4个条件可能不一定都有，使用StringBuilder添加条件
         *
         *//*
        StringBuilder sql=new StringBuilder("select * from t_customer where 1=1 ");

        List<Object> params=new ArrayList<>();
        String cname=customer.getCname();
        //如果用户名不为null不为空
        if(cname!=null&&!cname.trim().isEmpty()){
            sql.append("and cname like ?");
            //添加参数
            params.add("%"+cname+"%");
        }

        String gender=customer.getGender();
        //如果用户名不为null不为空
        if(gender!=null&&!gender.trim().isEmpty()){
            sql.append("and gender=?");
            //添加参数
            params.add(gender);
        }

        String cellphone=customer.getCellphone();
        //如果用户名不为null不为空
        if(cellphone!=null&&!cellphone.trim().isEmpty()){
            sql.append("and cellphone like ?");
            //添加参数
            params.add("%"+cellphone+"%");
        }

        String email=customer.getEmail();
        //如果用户名不为null不为空
        if(email!=null&&!email.trim().isEmpty()){
            sql.append("and email like ?");
            //添加参数
            params.add("%"+email+"%");
        }

        return qr.query(sql.toString(),new BeanListHandler<Customer>(Customer.class),params.toArray());

    }*/




    public PageBean<Customer> query(Customer customer,int pc,int ps) throws SQLException {

        /**
         * 创建pagebean对象
         * 传入pc和ps
         */
        PageBean<Customer> pageBean=new PageBean<>();
        pageBean.setPc(pc);
        pageBean.setPs(ps);
        /**
         * 1、创建sql模板
         *      这里考虑到多个模板都需要4个条件，采取sql模板片段
         *
         */
        StringBuilder countSql=new StringBuilder("select count(*) from t_customer");
        StringBuilder whereSql=new StringBuilder(" where 1=1");

        List<Object> params=new ArrayList<>();
        String cname=customer.getCname();
        //如果用户名不为null不为空
        if(cname!=null&&!cname.trim().isEmpty()){
            whereSql.append(" and cname like ?");
            //添加参数
            params.add("%"+cname+"%");
        }

        String gender=customer.getGender();
        //如果用户名不为null不为空
        if(gender!=null&&!gender.trim().isEmpty()){
            whereSql.append(" and gender=?");
            //添加参数
            params.add(gender);
        }

        String cellphone=customer.getCellphone();
        //如果用户名不为null不为空
        if(cellphone!=null&&!cellphone.trim().isEmpty()){
            whereSql.append(" and cellphone like ?");
            //添加参数
            params.add("%"+cellphone+"%");
        }

        String email=customer.getEmail();
        //如果用户名不为null不为空
        if(email!=null&&!email.trim().isEmpty()){
            whereSql.append(" and email like ?");
            //添加参数
            params.add("%"+email+"%");
        }

        /**
         * 查询结果
         * 查询总记录数
         * 查询当前请求页记录
         */

        Number num=(Number) qr.query(countSql.append(whereSql).toString(),new ScalarHandler(),params.toArray());
        int tr=num.intValue();
        pageBean.setTr(tr);//设置总记录数

        StringBuilder custSql=new StringBuilder("select * from t_customer");
        StringBuilder limitSql=new StringBuilder(" limit ?,?");

        //params需要追加limit后面的两个参数
        params.add((pc-1)*ps);
        params.add(ps);
        //查询当前请求页需要的记录
        List<Customer> beanList=qr.query(custSql.append(whereSql).append(limitSql).toString(),
                new BeanListHandler<>(Customer.class),params.toArray());
        pageBean.setBeanList(beanList);
        return pageBean;

    }
}
