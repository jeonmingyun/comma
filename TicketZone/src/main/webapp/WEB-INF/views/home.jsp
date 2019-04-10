<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>번호요</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="resources/css/home.css" rel="stylesheet" />
<script src="resources/js/home.js"></script>
</head>
<body>

	<%@include file="include/header.jsp"%>
	
	<!-- 공지사항 div -->
	<div id="home">
		<h1>공지사항</h1>
		<div id="notice_table">
		<form id="searchForm" method="get" action = "/">
		<select name="type">
			<option value="T">제목</option>
			<option value="C">내용</option>
			<option value="TC">제목 or 내용</option>
		</select>
		<input type="text" name="keyword" />
		<input type="hidden" id="efg" name="pageNum" value = "${pageMaker.cri.pageNum}">
		<input type="hidden" id="abcd" name="amout" value = "${pageMaker.cri.amount}">
		<button class="btn-default">검색</button>
		</form>

		<table id="notice">
			<tr>
				<th>번호
				<th>제목
				<th>작성자
				<th>날짜
			</tr>
		 	<c:forEach var="nl" items="${noticeList}">
				<tr onclick="showNotice(${nl.notice_no})">
					<td>${nl.notice_no}
					<td>${nl.notice_title}
					<td>관리자
					<td>${nl.notice_reg}
				</tr>
			</c:forEach>			
		</table>
		<div id="writebutton">
		<!-- play store button div-->
		<button id="noticeWrite" onclick="noticeWrite()">글쓰기</button>
		</div>
		</div>
	</div>
	
	<!-- Paging -->
	<div class='pull-right'>
		<ul class="pagination">
			<c:if test="${pageMaker.prev}">
				<li class="paginate_button previous"><a href="${pageMaker.startPage -1}"><</a></li>
			</c:if>
			
			<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
				<li class="paginate_button"><a href="${num}">${num}</a></li>
			</c:forEach>
			
			<c:if test="${pageMaker.next}">
				<li class="paginate_button next"><a href="${pageMaker.endPage +1 }">></a></li>
			</c:if>
		</ul>
	</div>
	<form id='actionForm' action='/' method='get'>
		<input type="hidden" id="efg" name="pageNum" value = "${pageMaker.cri.pageNum}">
		<input type="hidden" id="abcd" name="amout" value = "${pageMaker.cri.amount}">
		<input type="hidden" name="type" value='<c:out value="${pageMaker.cri.type}"/>'>
		<input type="hidden" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"/>'>
	</form>
	<!-- end Pagination -->
	
	<div id="app">
		<h1>번호요 지금 바로 이용하기</h1>
		<a href="https://play.google.com/store/apps/details?id=com.soonbuny">
			<img alt="no image" src="resources/img/playstore.png">
		</a>
	</div>
	
	<%@include file="include/footer.jsp"%>
	

</body>
</html>
