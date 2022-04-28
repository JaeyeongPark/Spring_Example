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
	function del(){
		document.getElementById("rform").action="delmember.do";
		document.getElementById("rform").submit();
	}
	
	function update(){
		document.getElementById("rform").action="updatemember.do";
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
					<mark class="sky">회원수정/삭제</mark>
				</h2>
				<div class="form-group">
					<label for="id">아이디</label> <input type="text" class="form-control"
						name="id" id="id" value="${param.id }"/>
				</div>
				<div class="form-group">
					<label for="pw">비밀번호</label> <input type="password"
						class="form-control" name="pw" id="pw" value="${param.pw }"/>
				</div>
				<div class="form-group">
					<label for="name">이름</label> <input type="text" name="name"
						class="form-control" id="name" value="${param.name }"/>
				</div>
				<div class="form-group">
					<label for="addr">주소</label> <input type="text"
						class="form-control" name="addr" id="addr" value="${param.addr }"/>
				</div>
				<div class="form-group">
					<label for="age">나이</label> <input type="text" class="form-control"
						name="age" id="age" value="${param.age }"/>
				</div>
				<div class="form-group">
					<input type="button" class="btn btn-outline-danger" value="삭제" onclick="del()">
					<input type="button" class="btn btn-outline-primary" value="수정" onclick="update()">
				</div>

			</form>
			<a href="initpage.do">초기화면</a>
		</div>
	</div>
</body>
</html>