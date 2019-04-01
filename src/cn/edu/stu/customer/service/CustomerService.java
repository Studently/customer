package cn.edu.stu.customer.service;

import cn.edu.stu.customer.dao.CustomerDao;
import cn.edu.stu.customer.domain.Customer;

import java.sql.SQLException;

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
}
