<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<%-- 
	
	//(String)session.getAttribute("id");
	String id = null;  
	if(id==null){
		Cookie[] cookies = (Cookie[])request.getCookies();
		for(Cookie co : cookies){
			if(co.getName().equals("id")){
				id = co.getValue();
			}
		}
	}
--%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	로그인 정보 : <span>
	<c:choose>
		<c:when test="${not empty id }">
			${id }
		</c:when>
		<c:otherwise>
			로그인 되지 안았습니다	
		</c:otherwise>
	</c:choose>
	
	</span>
</body>
</html>

