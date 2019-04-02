<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<h3 align="center">客户列表</h3>
<table border="1" width="70%" align="center">
	<tr>
		<th>客户姓名</th>
		<th>性别</th>
		<th>生日</th>
		<th>手机</th>
		<th>邮箱</th>
		<th>描述</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${requestScope.pb.beanList}" var="cl">
		<tr>
			<td>${cl.cname}</td>
			<td>${cl.gender}</td>
			<td>${cl.birthday}</td>
			<td>${cl.cellphone}</td>
			<td>${cl.email}</td>
			<td>${cl.description}</td>
			<td>
				<a href="<c:url value='/CustomerServlet?method=preEdit&cid=${cl.cid}'/>">编辑</a>
				<a href="<c:url value='/CustomerServlet?method=delete&cid=${cl.cid}'/>">删除</a>
			</td>
		</tr>
	</c:forEach>

</table>
<br/>
  <center>
	  第${requestScope.pb.pc}页/共${requestScope.pb.tp}页
	  <a href="${requestScope.pb.url}&pc=1">首页</a>
<c:if test="${requestScope.pb.pc > 1}">
	  <a href="${requestScope.pb.url}&pc=${requestScope.pb.pc-1}">上一页</a>
</c:if>


	  <c:choose>
		  <%--如果总页数小于等于10，全部显示出来--%>
		  <c:when test="${requestScope.pb.tp<=10}">
			  <c:set var="begin" value="1"/>
			  <c:set var="end" value="${requestScope.pb.tp}"/>
		  </c:when>
		<%--如果总页数大于10--%>
		  <c:otherwise>
			  <%--保持当前页始终在第六位--%>
			  <c:set var="begin" value="${requestScope.pb.pc-5}"/>
			  <c:set var="end" value="${requestScope.pb.pc+4}"/>

			  <%--头溢出--%>
			  <c:if test="${begin <1}">
				  <c:set var="begin" value="1"/>
				  <c:set var="end" value="10"/>
			  </c:if>

			  <%--尾溢出--%>
			  <c:if test="${end > requestScope.pb.tp}">
				  <c:set var="end" value="${requestScope.pb.tp-9}"/>
				  <c:set var="end" value="${requestScope.pb.tp}"/>
			  </c:if>
		  </c:otherwise>
	  </c:choose>

	  <c:forEach var="i" begin="${begin}" end="${end}">
		  <c:choose>
			  <c:when test="${requestScope.pb.pc eq i}">
				  [${i}]
			  </c:when>
			  <c:otherwise>
				  <a href="${requestScope.pb.url}&pc=${i}">[${i}]</a>
			  </c:otherwise>
		  </c:choose>



	  </c:forEach>


<c:if test="${requestScope.pb.pc < requestScope.pb.tp}">
	  <a href="${requestScope.pb.url}&pc=${requestScope.pb.pc+1}">下一页</a>
</c:if>
	  <a href="${requestScope.pb.url}&pc=${requestScope.pb.tp}">尾页</a>
  </center>
  </body>
</html>
