<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<h2 class="t_xsrm">
		新书热卖榜
	</h2>
	<div id="NewProduct_1_o_t" onmouseover="">
	<ul>
	<c:forEach items="${newAndHot}" var="book">
		<li>&nbsp;&nbsp;&nbsp;
			<a  target='_blank' href="${pageContext.request.contextPath}/book/selectOneBook?id=${book.id}">
				${book.name}
			</a>
		</li>
	</c:forEach>
	</ul>
	<h3 class="second">
		<span class="dot_r"> </span><a href="#" target="_blank">更多&gt;&gt;</a>
	</h3>
</div>