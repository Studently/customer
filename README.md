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
  实现效果：
  第N页/共M页　首页 上一页 1 2 3 4 5 6 7 8 9 10　下一页 尾页
  使用一个pagebean实体类来存放分页数据：
    private int pc;//当前页码 page current
    private int tp;//总页数 total page
    private int tr;//总记录 total record
    private int ps;//每页的条数 page size
    private List<T> beanList;//当前页数据
    private String url;//存放访问连接
  页码显示参考百度的页码效果：
    最多显示10个页码
    当前页码显示页码列表的第六个位置
  显示页码需要直到开始(begin)和结束(end)两个页码的位置，但是这里当前页码位置可以推算出开始和结束页码数。
    10 11 12 13 14 (15) 16 17 18 19
    需要使用当前页pc来推算出begin和end
    begin = pc – 5
    end = pc + 4
  计算公式：
    如果总页数<=10（列表长度），那么begin=1，end=总页数
    使用公式计算；begin=pc-5, end=pc + 4；
    头溢出：当begin<1时，让begin=1
    尾溢出：当end>总页数时，让end=总页数

  最困难的是多条件组合查询时分页查询
  多条件组合查询后，点击页码跳转的超链接没有条件了，这里使用PageBean的url中！这个任务交给Servlet！
