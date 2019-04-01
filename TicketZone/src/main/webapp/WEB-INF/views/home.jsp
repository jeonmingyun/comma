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
	<div>
		<h1>공지사항</h1>
		<table id="notice">
			<tr>
				<td>번호
				<td>제목
				<td>작성자
				<td>날짜
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
		
	</div>
	<button id="noticeWrite" onclick="noticeWrite()">글쓰기</button>
	
	<!-- play store button div-->
	<div>
		<h1>번호요 지금 바로 이용하기</h1>
		<img alt="no image" src="resources/img/playstore.png">
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
	</form>
	<!-- end Pagination -->
	<%@include file="include/footer.jsp"%>

</body>
</html>
