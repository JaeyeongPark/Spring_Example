<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, edu.ssafy.dto.MemDTO "%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="ko_KR"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트 조회</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style type="text/css">
mark.sky {
	background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}
</style>
</head>
<body>
	<div class="container text-center mt-3">
		<h2 class="p-3 mb-3 shadow bg-light">
			<mark class="sky">회원조회</mark>
		</h2>
		<div class="col-lg-8 mx-auto">
			<table class="table table-active test-left" border="1">
				<tr class="table-info">
					<td>id</td>
					<td>pw</td>
					<td>name</td>
					<td>addr</td>
					<td>age</td>
					<td>age</td>
					<td>age</td>
				</tr>
				<c:forEach items="${list }" var="mem" >
				<tr>
					<td><a href="viewmember.do?id=${mem.id }&pw=${mem.pw }&name=${mem.name }&addr=${mem.addr }&age=${mem.age }">${mem.id }</a></td>
					<td>${mem.pw }</td>
					<td>${mem.name }</td>
					<td>${mem.addr }</td>
					<td>${mem.age }</td>
					<td><fmt:formatNumber value="${mem.age }" pattern="#,000.0"/></td>
					<td><fmt:formatNumber value="${mem.age }" type="currency"/></td>
				</tr>
				</c:forEach>
			</table>
			<a href="initpage.do">초기화면</a>
		</div>
	</div>
</body>
</html>

