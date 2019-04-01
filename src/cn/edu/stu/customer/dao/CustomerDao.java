package cn.edu.stu.customer.dao;

import cn.edu.stu.customer.domain.Customer;
import cn.edu.stu.tools.jdbc.TrQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
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
     * 查询所有
     * @throws SQLException
     */
    public List<Customer> findAll() throws SQLException {

        //创建sql模板
        String sql="select * from t_customer";

        //查询结果，返回list
        return qr.query(sql, new BeanListHandler<Customer>(Customer.class));
    }

}
