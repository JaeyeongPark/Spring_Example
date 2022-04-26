<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
    
  

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
<script type="text/javascript">
	function login(){
		document.getElementById("rform").action="login.do";
		document.getElementById("rform").submit();
	}
</script>
<title>회원 수정/삭제</title>
</head>
<body>

	<div class="container text-center mt-3">

		<div class="col-lg-4 mx-auto">
			<form action="regmember.do" class="text-left mb-3" method="post"
				id="rform">
				<h2 class="p-3 mb-3 text-center shadow bg-light">
					<mark class="sky">로그인</mark>
				</h2>
				<div class="form-group">
					<label for="id">아이디</label> <input type="text" class="form-control"
						name="id" id="id" placeholder="아이디를 입력하세요"/>
				</div>
				<div class="form-group">
					<label for="pw">비밀번호</label> <input type="password"
						class="form-control" name="pw" id="pw" placeholder="패스워드를 입력하세요"/>
				</div>
				<div class="form-group">
					<input type="button" class="btn btn-outline-primary" value="로그인" onclick="login()">
				</div>

			</form>
			<a href="initpage.do">초기화면</a>
		</div>
	</div>
</body>
</html>