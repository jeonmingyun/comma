<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="resources/js/mngrOnly/mInquiry.js"></script>
<!-- <link href="resources/css/mInquiry.css" rel="stylesheet" /> -->
<link href="resources/css/inquiry.css" rel="stylesheet" />
<title>번호요 - 점주</title>
</head>
<body>

	<div>
		<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
		
		<section>
			<div id="suggest_form">
			<!-- 건의사항 -->
				<h2>건의사항</h2>
				<div class="divider_1"><div class="line"></div></div>
				
				<div id="inquiry_table">
				<form id="searchForm" method="get" action="/mInquiry">
					<select name="type">
						<option value="T">제목</option>
						<option value="C">내용</option>
						<option value="TC">제목 or 내용</option>
					</select> <input type="text" name="keyword" />
					<input type="hidden" id="efg" name="pageNum" value="${pageMaker.cri.pageNum}">
					<input type="hidden" id="abcd" name="amout" value="${pageMaker.cri.amount}">
					<button class="btn-default">검색</button>
				</form>
				
				<table id="suggest">
					<tr>
						<th style="width:5%;">번호</th>
						<th style="width:5%;"> </th>
						<th style="width:30%;">제목</th>
						<th style="width:10%;">작성자</th>
						<th style="width:20%;">작성일</th>
					</tr>
					<c:forEach var="sl" items="${suggestList}">
						<tr onclick="mShowInquiry(${sl.board_no})">
							<td>${sl.board_no}
							<td><c:if
									test="${!empty sl.board_password}">
									<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 24 24"><path d="M0 0h24v24H0z" fill="none"/><path d="M18 8h-1V6c0-2.76-2.24-5-5-5S7 3.24 7 6v2H6c-1.1 0-2 .9-2 2v10c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V10c0-1.1-.9-2-2-2zm-6 9c-1.1 0-2-.9-2-2s.9-2 2-2 2 .9 2 2-.9 2-2 2zm3.1-9H8.9V6c0-1.71 1.39-3.1 3.1-3.1 1.71 0 3.1 1.39 3.1 3.1v2z"/></svg>
								</c:if>
							<td>${sl.board_title}				
							<td>${sl.member_tel}
							<td><fmt:formatDate value="${sl.board_reg}" pattern="yyyy.MM.dd"/>
						</tr>
					</c:forEach>
				</table>
				<div id="writebutton">
				<button type="button" id="btnwrite">글쓰기</button>
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
	<form id='actionForm' action='/mInquiry' method='get'>
		<input type="hidden" id="efg" name="pageNum" value = "${pageMaker.cri.pageNum}">
		<input type="hidden" id="abcd" name="amout" value = "${pageMaker.cri.amount}">
		<input type="hidden" name="type" value='<c:out value="${pageMaker.cri.type}"/>'>
		<input type="hidden" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"/>'>
	</form>
	<!-- end Pagination -->
		</section>
		<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</div>
</body>
</html>