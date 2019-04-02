package cn.edu.stu.customer.service;

import cn.edu.stu.customer.dao.CustomerDao;
import cn.edu.stu.customer.domain.Customer;
import cn.edu.stu.customer.domain.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * 业务层
 */
public class CustomerService {

    //依赖dao层
    CustomerDao customerDao=new CustomerDao();

    /**
     * 插入用户信息
     * @param customer
     * @throws SQLException
     */
    public void add(Customer customer) throws SQLException {
        customerDao.add(customer);
    }

    /**
     * 分页查询所有
     * @param pc 当前请求页
     * @param ps 每页大小
     * @return
     * @throws SQLException
     */
    public PageBean<Customer> findAll(int pc, int ps) throws SQLException {
        return customerDao.findAll(pc,ps);
    }


    /**
     * 通过用户cid加载用户信息
     * @param cid
     * @return
     * @throws SQLException
     */
    public Customer load(String cid) throws SQLException {
        return customerDao.load(cid);
    }


    /**
     * 编辑用户
     * @param customer
     * @throws SQLException
     */
    public void edit(Customer customer) throws SQLException {
        customerDao.edit(customer);
    }

    /**
     * 根据用户cid删除用户信息
     * @param cid
     * @throws SQLException
     */
    public void delete(String cid) throws SQLException {
        customerDao.delete(cid);
    }


    /**
     * 多条件组合查询
     * @param customer
     * @throws SQLException
     */
    public PageBean<Customer> query(Customer customer,int pc,int ps) throws SQLException {
        return customerDao.query(customer,pc,ps);
    }
}
