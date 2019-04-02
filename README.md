# customer
客户关系管理系统：实现增删该查

版本：1.0 增删改查
  第一阶段：项目框架的搭建
  首先是导入页面原型：页面是之前就有的，这里只是来完成后台逻辑
  导入所需依赖的jar包：
    c3p0-0.9.2-pre1.jar
    commons-dbutils-1.4.jar
    jstl-1.2.jar
    mchange-commons-0.2.jar
    mysql-connector-java-5.1.46.jar
    Uilts.jar（这是自己封装的一些工具类）

   建立项目各层目录：
   创建包：cn.edu.stu.customer
    domain：Customer
    dao：CustomerDao
    service：CustomerService
    servlet：CustomerServlet
    在src目录下添加c3p0-config.xml文件

   第二阶段：添加功能
     添加客户
     查询所有客户
     修改客户信息
     删除客户信息
     多条件组合查询
     
版本：2.0 分页查询

  
