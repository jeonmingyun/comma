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
<script src="../resources/js/mngrOnly/mUpdInquiry.js"></script>
<style media="screen">
</style>
<script>
	
</script>
</head>

<body>
	<h2>게시글 보기</h2>
	<form method="post" action="/mUpdInquiryForm">
		<input type="hidden" value="${InquiryUpd[0].board_no}" name="board_no">
		<div>문의 유형</div>
		<div>
			제목 <input name="board_title" id="title" size="80" value="${InquiryUpd[0].board_title}">
		</div>
		<div>
			내용
			<textarea name="board_content" id="content" rows="4" cols="80">${InquiryUpd[0].board_content}</textarea>
		</div>
		<div>첨부파일</div>
		<div>
			<input type="submit" id="update" value="수정">
			<input type="button" id="cancel" value="취소">
		</div>
	</form>
</body>
</html>