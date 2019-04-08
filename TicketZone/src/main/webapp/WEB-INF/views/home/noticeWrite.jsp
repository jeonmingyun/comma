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
</head>
<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<form method="post" action="/noticeInsert">
		제목:<input type="text" id="notice_title" name="notice_title"><br>
		내용:
		<textarea id="notice_content" name="notice_content"></textarea>
		<br> <input type="submit" value="글작성">
	</form>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>