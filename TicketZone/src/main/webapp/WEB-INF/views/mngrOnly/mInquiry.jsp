<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="resources/js/mngrOnly/mInquiry.js"></script>
<link href="resources/css/mInquiry.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>

	<div>
		<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
		<section>
			<div id="suggest_form">
				<h2>건의사항</h2>
				<!-- 건의사항 -->
				<form id="searchForm" method="get" action="/mInquiry">
					<select name="type">
						<option value="T">제목</option>
						<option value="C">내용</option>
						<option value="TC">제목 or 내용</option>
					</select> <input type="text" name="keyword" />
					<input type="hidden" id="efg" name="pageNum" value="${pageMaker.cri.pageNum}">
					<input type="hidden" id="abcd" name="amout" value="${pageMaker.cri.amount}">
					<button class="btn btn-default">검색</button>
				</form>
				<button type="button" id="btnwrite">글쓰기</button>
				<table id="suggest">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
					<c:forEach var="sl" items="${suggestList}">
						<tr onclick="mShowInquiry(${sl.board_no})">
							<td>${sl.board_no}
							<td>${sl.board_title}
								<c:if
									test="${!empty sl.board_password}">
									<img src="resources/icon/baseline-lock-24px.svg" alt="locked"
										height="15" width="15" />
								</c:if>
							<td>${sl.member_tel}
							<td>${sl.board_reg}
						</tr>
					</c:forEach>
				</table>
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