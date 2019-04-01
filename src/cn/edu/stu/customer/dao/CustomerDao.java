package cn.edu.stu.customer.dao;

import cn.edu.stu.customer.domain.Customer;
import cn.edu.stu.tools.jdbc.TrQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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

}
