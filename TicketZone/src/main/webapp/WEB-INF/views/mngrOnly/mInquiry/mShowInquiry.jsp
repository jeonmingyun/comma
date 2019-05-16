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
<title>Document</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="../resources/js/mngrOnly/mShowInquiry.js"></script>
<link href="../resources/css/mShowInquiry.css" rel="stylesheet" />
<!-- <link href="../resources/css/showInquiry.css" rel="stylesheet" /> -->
<style media="screen">
</style>
<script>
	
</script>
</head>
<body>
	<div class="minquiry_wapper">
		<div class="page_title">
			<h1>건의사항</h1>
		</div>
		<!-- page_title -->

		<div class=minquiry_view>
			<div class="minquiry_title">
				<p id="title">${InquiryUpd[0].board_title}</p>
			</div>
			<!-- minquiry_title -->

			<div class="minquiry_info">
				<span id="writer"><svg xmlns="http://www.w3.org/2000/svg"
						width="15" height="15" viewBox="0 0 24 24">
						<path
							d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z" />
						<path d="M0 0h24v24H0z" fill="none" /></svg>관리자</span> <span id="date"><svg
						xmlns="http://www.w3.org/2000/svg" width="15" height="15"
						viewBox="0 0 24 24">
						<path
							d="M11.99 2C6.47 2 2 6.48 2 12s4.47 10 9.99 10C17.52 22 22 17.52 22 12S17.52 2 11.99 2zM12 20c-4.42 0-8-3.58-8-8s3.58-8 8-8 8 3.58 8 8-3.58 8-8 8z" />
						<path d="M0 0h24v24H0z" fill="none" />
						<path d="M12.5 7H11v6l5.25 3.15.75-1.23-4.5-2.67z" /></svg> <fmt:formatDate
						value="${InquiryUpd[0].board_reg}" pattern="yyyy.MM.dd HH:mm:ss" />
				</span>
			</div>
			<!-- minquiry_info -->

			<!-- 게시판 내용 -->
			<div class="minquiry_content">${InquiryUpd[0].board_content}</div>
			<!-- minquiry_content -->

			<!-- 첨부파일 -->
			<div class="minquiry_file">
				<span id="date">첨부파일</span>
			</div>
			<!-- minquiry_file -->

		</div>
		<!-- minquiry_view -->

		<!-- <div>inquiry_reply -->
			<%@include file="../../include/reply.jsp"%>
		<!-- </div> -->
		<p>
		<div class="minquiry_btn">
			<button type="button" id="btnUpdate"
				onclick="updInquiry(${InquiryUpd[0].board_no})">수정</button>
			<button type="button" id="btnDelete"
				onclick="delInquiry(${InquiryUpd[0].board_no})">삭제</button>
		</div>
		<!-- minquiry_btn -->
	</div>
	<!-- minquiry_wapper -->

</body>
</html>