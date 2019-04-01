package cn.edu.stu.customer.web.servlet;

import cn.edu.stu.customer.service.CustomerService;
import cn.edu.stu.tools.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * web层
 */
@WebServlet(name = "CustomerServlet")
public class CustomerServlet extends BaseServlet {

    //依赖业务层
    private CustomerService customerService=new CustomerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
