<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업/다운로드</title>
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
	<!-- 
		1. 파일업로드 입력폼을 만든다
		2. action  을 처리할 controller 를 만든다
		3. file 처리는 method 를 post 로 한다
		4. enctype 은 multipart/form-data 설정		(기본은 :  x-www.form-urlencode)
	    5. input  type 을 file 로 하고 submit 한다
	 -->
	<div class="container text-center mt-3">
		<form action="${root }/fileupload" method="post"
			enctype="multipart/form-data">
			파일 : <input type="file" name="upfile" multiple="multiple"> <input
				type="submit" value="업로드">
		</form>
		
		<table border="1" align="center" class="mt-2">
			<c:if test="${not empty files }">
				<tr>
					<th>no</th>
					<th>name</th>
					<th>path</th>
				</tr>
				<c:forEach items="${files }" var="file">
					<tr>
						<th><a href='<c:url value="/filedownload?no=${file.no }"></c:url>'>${file.no}</a></th>
						<th>${file.name}</th>
						<th>${file.path}</th>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty files }">
				<h5 class="mt-2">저장된 파일이 없습니다</h5>
			</c:if>
		</table>
	</div>


</body>
</html>


