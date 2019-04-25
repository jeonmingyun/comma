<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="resources/js/NoticeWrite.js"></script>
<link href="resources/css/noticeWrite.css" rel="stylesheet"/>
</head>
<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<form method="post" id="insert_from" action="/noticeInsert">
		제목:<input type="text" id="notice_title" name="notice_title"><br>
		내용:
		<textarea id="notice_content" name="notice_content"></textarea>
		<!-- 파일업로드 -->
		<div class="uploadDiv">
			<input type="file" name="uploadFile" multiple>
		</div>
		<div class="resultDiv">
			
		</div>
		
		<br> <input type="submit" value="글작성"><br>		
	</form>
		<button id="write_cancle">취소</button>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>