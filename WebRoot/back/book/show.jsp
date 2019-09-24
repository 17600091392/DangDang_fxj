<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
  <head>
    <title>index.html</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/btn.css" />
    <meta charset="utf-8">
    
	<style type="text/css">
		table {
			border-right: 1px solid black;
			border-bottom: 1px solid black;
		}
		
		table td {
			padding: 10px;
			text-align:center;
			border-left: 1px solid black;
			border-top: 1px solid black;
		}
</style>
  </head>
  
  <body style="background-color: #f0f9fd;">
  	
  	<div align=center style="font-size:25px">图书管理</div><hr/>  	
  	<div style="margin: 0 auto;text-align:center;">
  		<form action="${pageContext.request.contextPath}/book/selectByKey" method="post">
  			<input onclick="location.href='${pageContext.request.contextPath}/category/selectSecond'" class="button btn-p" value="添加图书&raquo;" />&emsp;&emsp;
		  	<select name="key" class="el-select__inner inner2">
		  		<option value="name">书名</option>
		  		<option value="author">作者</option>
		  		<option value="press">出版社</option>
		  	</select>
		  	<input class="el-input__inner" type="text" placeholder="请输入查询条件" name="content"/>
		  	<input class="el-search-content" type="submit" value="搜索"/>
	  	</form>
  	</div>
  	<div style="margin-top:20px">
  	<table bordercolor='#898E90' align='center' border='3px' cellpadding='5px' cellspacing="0px" >
  		<tr bgcolor='lightblue' align='center'>
			<td style="width: 100px">名称</td>
			<td>作者</td>
			<td>定价</td>
			<td>当当价</td>
			<td>出版社</td>
			<td>出版时间</td>
			<td>封面图</td>
			<td>销量</td>
			<td>库存</td>
			<td>操作</td>
  		</tr>
  		
  		<c:forEach items="${list}" var="book">
		<tr align='center'>
			<td>${book.name}</td>
			<td>${book.author}</td>
			<td>${book.price}</td>
			<td>${book.dprice}</td>
			<td>${book.press}</td>
			<td><fmt:formatDate value="${book.print_date}" pattern="yyyy-MM-dd"/></td>	
			<td>
				<img width="32px" height="20px" src="${pageContext.request.contextPath}/back/img/${book.cover}">
			</td>
			<td>${book.sale}</td>
			<td>${book.stock}</td>
			<td>
				<input  class="button"  onclick="location.href='${pageContext.request.contextPath}/book/selectBook?id=${book.id}'" value="修改 " />
				<input class="button-del" onclick="location.href='${pageContext.request.contextPath}/book/deleteBook?id=${book.id}'" value="删除 " />
			</td>
		</tr>
		</c:forEach>
		
  		
  	</table>
  	</div><br>
  </body>
</html>
