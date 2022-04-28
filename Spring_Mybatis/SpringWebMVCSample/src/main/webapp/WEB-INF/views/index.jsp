<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <c:set var="id" value="test"/> --%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	document.getElementById();
</script>
</head>
<body>
	<c:choose>
		<c:when test="${not empty id }">
			<jsp:include page="member/header.jsp"></jsp:include>
			<h1>
				<a href="get.do?name=홍길동&age=21">get 방식 호출</a>
			</h1>
			<form action="post.do" method="post">
				<input type="text" name="name"></input> <input type="text"
					name="age"></input> <br> <input type="checkbox" name="hobby"
					value="독서">독서</input> <input type="checkbox" name="hobby"
					value="공부">공부</input> <input type="checkbox" name="hobby"
					value="오락">오락</input> <input type="submit" value="전송"></input>
			</form>
			<h1><a href="${root}/mem/loginmemberform.do">로그인</a></h1>
			<h1><a href="${root}/mem/logout.do">로그아웃</a></h1>
			<h1><a href="<c:url value='/mem/regmemberform.do'></c:url>">회원가입</a></h1>
			<h1><a href="<c:url value='/mem/listmember.do'></c:url>">회원리스트조회</a></h1>
			<h1><a href="<c:url value='/filepage'></c:url>">파일 업/다운로드</a></h1>
			
		</c:when>
		<c:otherwise>
			<h1><a href="${root}/mem/loginmemberform.do">로그인</a></h1>
			<h1><a href="<c:url value='/mem/regmemberform.do'></c:url>">회원가입</a></h1>
		</c:otherwise>
	</c:choose>
</body>
</html>