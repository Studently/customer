package cn.edu.stu.customer.service;

import cn.edu.stu.customer.dao.CustomerDao;

/**
 * 业务层
 */
public class CustomerService {

    //依赖dao层
    CustomerDao customerDao=new CustomerDao();
}
