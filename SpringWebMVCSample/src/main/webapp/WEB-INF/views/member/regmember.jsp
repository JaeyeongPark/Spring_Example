<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<c:set var="root" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style type="text/css">
mark.sky {
	background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		$('#check').on('click',function(){
			$.ajax({
				url:'${root}/mem/idcheck',
				method:'post',
				data:{
					id : $('#id').val()
				},
				dataType:'json',
				success:function(data,status,xhr){
					alert(data.res);
				},
				error:function(err){
					console.log(err);
				}
			});
			
		});
	});

</script>
<title>회원 가입</title>
</head>
<body>

	<div class="container text-center mt-3">

		<div class="col-lg-4 mx-auto">
			<form action="regmember.do" class="text-left mb-3" method="post"
				id="rform">
				<h2 class="p-3 mb-3 text-center shadow bg-light">
					<mark class="sky">회원가입</mark>
				</h2>
				<div class="form-group">
					<label for="id">아이디</label> <input type="text" class="form-control"
						name="id" id="id" />
					<input type="button" value="아이디중복체크" id="check" class="btn btn-primary mt-1">	
				</div>
				<div class="form-group">
					<label for="pw">비밀번호</label> <input type="password"
						class="form-control" name="pw" id="pw" />
				</div>
				<div class="form-group">
					<label for="name">이름</label> <input type="text" name="name"
						class="form-control" id="name" />
				</div>
				<div class="form-group">
					<label for="addr">주소</label> <input type="text"
						class="form-control" name="addr" id="addr" />
				</div>
				<div class="form-group">
					<label for="age">나이</label> <input type="text" class="form-control"
						name="age" id="age" />
				</div>
				<div class="form-group">
					<input type="reset" class="btn btn-outline-danger" value="취소">
					<input type="submit" class="btn btn-outline-primary" value="등록">
				</div>

			</form>
			<a href="initpage.do">초기화면</a>
		</div>
	</div>
</body>
</html>