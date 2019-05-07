<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script src="resources/js/updInquiry.js"></script>
<style media="screen">
</style>
<script>
	
</script>
</head>

<body>
	<h2>게시글 보기</h2>
	<!-- <form method="post" action="/updInquiryForm"> -->
		<input type="hidden" value="${InquiryUpd[0].board_no}" name="board_no">
		<div>문의 유형</div>
		<div>
			제목 <input name="board_title" id="title" size="80" value="${InquiryUpd[0].board_title}">
		</div>
		<div>
			내용
			<textarea name="board_content" id="content" rows="4" cols="80">${InquiryUpd[0].board_content}</textarea>
		</div>
		<span id="date">첨부파일</span>
			<div id="uploadFile">
			<c:if test="${file[0] != null}"><a href="#">${file[0].inq_filename}</a><button id="deleteFile">삭제하기</button></c:if><c:if test="${file[0] == null}"></c:if>
			<br>
			</div>
			<input type="file" name="uploadFile">		
		<div>
			<button id="update">수정</button>
			<button id="cancel">취소</button>
		</div>
		<div class="modifyDiv"></div>
		<c:if test="${file[0] != null}">
		<input id="inq_uuid" type="hidden" name="inq_uuid" value="${file[0].inq_uuid}">
		<input id="inq_uploadpath" type="hidden" name="inq_uploadpath" value="${file[0].inq_uploadpath}">
		<input id="inq_filename" type="hidden" name="inq_filename" value="${file[0].inq_filename}">		
		</c:if>
	<!-- </form> -->
</body>
</html>