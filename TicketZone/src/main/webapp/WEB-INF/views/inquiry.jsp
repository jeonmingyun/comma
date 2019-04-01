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
<link href="resources/css/inquiry.css" rel="stylesheet" />
<script src="resources/js/inquiry.js"></script>
</head>
<body>

	<div>
		<%@include file="include/header.jsp"%>

		<section>
			<div id="suggest_form">
				<h2>문의하기</h2>
				<!-- 문의하기 -->
				<div id=suggest_type2>
					<select>
						<option value="title">글제목</option>
						<option value="error">앱 오류</option>
						<option value="else">기타</option>
					</select> <input type="search"> <input type="button" value="검색">
					<button type="button" id="btnwrite">글쓰기</button>
				</div>

				<table id="suggest">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
					<c:forEach var="sl" items="${suggestList}">
						<tr onclick="showInquiry(${sl.board_no})">
							<td>${sl.board_no}
							<td>${sl.board_title}<c:if
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
		</section>

		<%@include file="include/footer.jsp"%>
	</div>
</body>
</html>
