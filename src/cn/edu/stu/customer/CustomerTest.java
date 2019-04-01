package cn.edu.stu.customer;

import cn.edu.stu.customer.domain.Customer;
import cn.edu.stu.customer.service.CustomerService;
import cn.edu.stu.tools.CommonUtils;
import org.junit.Test;

import java.sql.SQLException;

public class CustomerTest {
    @Test
    public void func() throws SQLException {

        CustomerService service=new CustomerService();

        for(int i=0;i<300;i++){
            Customer c=new Customer();
            c.setCid(CommonUtils.uuid());
            c.setCname("cst_"+i);
            c.setBirthday("2019-04-01");
            c.setCellphone("176"+i);
            c.setGender((i%2==0) ? "男":"女");
            c.setEmail(c.getCname()+"@qq.com");
            c.setDescription("我是客户");
            service.add(c);
        }
    }
}
