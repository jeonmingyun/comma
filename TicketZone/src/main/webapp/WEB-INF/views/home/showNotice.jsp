<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="../resources/js/showNotice.js"></script>
<link href="../resources/css/showNotice.css" rel="stylesheet" />
<style media="screen">
</style>
<script>
	
</script>
</head>
<body>
<%@include file="../include/header.jsp"%>
	<div class="notice_wapper">
		<div class="page_title">
			<h1>NOTICE</h1>
		</div> <!-- page_title -->

		<div class="notice_view">
			<div class="notice_title">
				<p id="title">${noticeUpd[0].notice_title}</p>
			</div><!-- notice_title -->
		
			<div class="notice_info">
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
						<path d="M12.5 7H11v6l5.25 3.15.75-1.23-4.5-2.67z" /></svg>
						<fmt:formatDate value="${noticeUpd[0].notice_reg}" pattern="yyyy.MM.dd HH:mm:ss"/>
						</span>
			</div> <!-- notice_info -->
			
			<!-- 게시판 내용 -->
			<div class="notice_content">
				${noticeUpd[0].notice_content}
			</div><!-- notice_content -->
			
			<!-- 첨부파일 -->
			<div class="notice_file">
				<span id="date">첨부파일</span>
				<c:if test="${file[0] != null}"><a href="#"class="downFile">${file[0].fileName}</a></c:if><c:if test="${file[0] == null}">없음</c:if>
			</div> <!-- notice_file -->
		
	<c:if test="${file[0] != null}">
		<div class="uploadResult">
			<ul>
				
			</ul>
		</div>
	</c:if>	
		<c:if test="${file[0] != null}">
		<input id="uuid" type="hidden" name="uuid" value="${file[0].uuid}">
		<input id="uploadPath" type="hidden" name="uploadPath" value="${file[0].uploadPath}">
		<input id="fileName" type="hidden" name="fileName" value="${file[0].fileName}">
	</c:if>
	</div> <!-- notice_view -->		
		
	<div class="notice_btn">
		<button type="button" id="btnUpdate"
			onclick="updNotice(${noticeUpd[0].notice_no})">수정</button>
		<button type="button" id="btnDelete"
			onclick="delNotice(${noticeUpd[0].notice_no})">삭제</button>
	</div> <!-- notice_btn -->
</div> <!-- notice_wapper -->
	
</body>
</html>