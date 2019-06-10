<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>번호요</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<!-- 기존의 CSS, JS -->

<!-- <link href="resources/css/home.css" rel="stylesheet" /> -->
<link href="resources/css/notice.css" rel="stylesheet" />
<script src="resources/js/home.js"></script>

<!-- Bootstrap core CSS -->
<link href="../resources/bootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../resources/bootstrap/css/modern-business.css" rel="stylesheet">

</head>
<body>

<!-- header -->
<%@include file="include/header.jsp"%>

	<!-- 공지사항 div -->
	<div id="home">
		<h2>공지사항</h2>
		<div class="divider_1"><div class="line"></div></div>
		
		<div id="notice_table">
		<form id="searchForm" method="get" action = "/notice">
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
				<th style="width:5%;">번호</th>
				<th style="width:30%;">제목</th>
				<th style="width:10%;">작성자</th>
				<th style="width:20%;">날짜</th>
			</tr>			
		 	<c:forEach var="nl" items="${noticeList}">
				<tr onclick="showNotice(${nl.notice_no})">
					<td>${nl.notice_no}
					<td>${nl.notice_title}
					<td>관리자
					<td><fmt:formatDate value="${nl.notice_reg}" pattern="yyyy.MM.dd"/>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
			
				<td>검색결과가없습니다.
			
			</c:if>			
		</table>
		<c:if test="${!empty sessionScope.admin}">
		<div id="writebutton">
		<!-- play store button div-->
		<button id="noticeWrite" onclick="noticeWrite()">글쓰기</button>
		</div>
		</c:if>	
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



	<footer>
		<%@include file="include/footer.jsp"%>
	</footer>

</body>
</html>
