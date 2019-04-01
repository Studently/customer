package cn.edu.stu.customer.service;

import cn.edu.stu.customer.dao.CustomerDao;
import cn.edu.stu.customer.domain.Customer;

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
     * 查询所有
     * @throws SQLException
     */
    public List<Customer> findAll() throws SQLException {
        return customerDao.findAll();
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
}
