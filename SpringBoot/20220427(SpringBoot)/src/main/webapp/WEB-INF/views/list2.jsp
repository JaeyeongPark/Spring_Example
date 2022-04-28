<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- jstl의 core 라이브러리를 사용하기 위해 taglib를 이용한다. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
form>label, form>select, form>input {
	margin-right: 10px !important;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div class="container">
		<h1>도서 목록</h1>

		<form method="get" action="${root }/list" id="searchForm" class="form-inline">
			<!-- 현재의 페이지 정보를 hidden으로 관리한다. -->
			<input type="hidden" name="currentPage" id="currentPage">
			<label>검색 컬럼:</label>
			<select name="key" id="key" class="form-control">
				<option value="none">없음</option>
				<option value="isbn">ISBN</option>
				<option value="title">제목</option>
				<option value="author">저자</option>
				<option value="content">내용</option>
			</select>
			<label>검색어:</label>
			<input type="text" name="word" id="word" class="form-control">
			<label>정렬 컬럼:</label>
			<select name="orderBy" id="orderBy" class="form-control mb-2">
				<option value="none">없음</option>
				<option value="isbn">ISBN</option>
				<option value="title">제목</option>
				<option value="author">저자</option>
				<option value="price">가격</option>
			</select>
			<label>정렬 방향:</label>
			<select name="orderByDir" id="orderByDir" class="form-control mb-2">
				<option value="asc">오름차순</option>
				<option value="desc">내림차순</option>
			</select>
			<div class="flex-grow-1 text-right">
				<!-- 클릭 시 전송되는 것을 막기 위해 type은 submit이 아니라 button이다. -->
				<input type="button" value="검색" class="btn btn-primary" id="btnSearch">
			</div>
		</form>
		<br>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>번호</th>
					<th>ISBN</th>
					<th>제목</th>
					<th>저자</th>
					<th>가격</th>
				</tr>
			</thead>
			<tbody id ="booklist"></tbody>
		</table>
		<br>
		<%@ include file="/WEB-INF/views/include/paging.jsp"%>
		<a href="${root }/regist">도서 등록</a>
	</div>
</body>
<script>
	// 전달받은 SearchCondition을 화면에 설정하는 함수를 만든다.
	
	$(document).ready(function(){
		
		$.ajax({
			url:'${root}/api/book',
			type: 'GET',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			success:function(books){
				makeList(books);
			},
			error:function(xhr, status, error){
				console.log("상태값: " + xhr.status + "\tHttp 에러메시지 : " + xhr.responseText);
			},
			statusCode: {
				500 : function(){
					alert("서버에러.");
				},
				404: function(){
					alert("페이지 없다.");
				}
			}
		});
	});
	
	function makeList(books){
		$("#booklist").empty();
		$(books).each(function(index, book){
			let str = `
				<tr id="view_${'${book.isbn}'}" class="view" data-id="${'${book.isbn}'}">
					<td>${'${index+1}'}</td>
					<td>${'${book.isbn}'}</td>
					<td>${'${book.title}'}</td>
					<td>${'${book.author}'}</td>
					<td>${'${book.price}'}</td>
				</tr>
				
				`;
			$("#booklist").append(str);	
			
		});
	}
	

	
	/* function setSearchCondition() {
		console.log("${searchCondition}")
		selectOptionByValue("orderBy", "${searchCondition.orderBy}");
		selectOptionByValue("orderByDir", "${searchCondition.orderByDir}");
		selectOptionByValue("key", "${searchCondition.key}");
		document.querySelector("#word").value = "${searchCondition.word}"
		document.querySelector("#currentPage").value = "${searchCondition.currentPage}"
	}
	
	// 화면 정보를 설정한다.
	setSearchCondition();

	// <select>요소에서 value 기준으로 <option>을 선택한다.
	function selectOptionByValue(selectId, value) {
		let select = document.querySelector("#" + selectId)
		let opts = select.options;
		for (let i = 0; i < opts.length; i++) {
			if (opts[i].value == value) {
				select.selectedIndex = i;
				break;
			}
		}
	}


	// page를 이용해서 자료를 검색한다. 함수 호출은 paging.jsp에서 이뤄진다.
	function movePage(page) {
		// currentPage에 전달받은 page를 저장한다.
		document.querySelector("#currentPage").value = page;
		// searchForm을 submit 시킨다.
		document.querySelector("#searchForm").submit();
	}
	
	// btnSearch가 클릭되면 currentPage를 1로 해서 검색 조건으로 검색한다.
	document.querySelector("#btnSearch").addEventListener("click", function(){
		movePage(1);
	}) */
</script>
</html>