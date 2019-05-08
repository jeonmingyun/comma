<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>비밀글</title>
<link href="../resources/css/board_pass_form.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="resources/js/board_pass_form.js"></script>

</head>
<body>

	<%@include file="../include/header.jsp"%>
	
    <form action="/board_pass_pro?board_no=${param.board_no}" method="post">
    
    <div id="secret_form">
    	<h1>비밀글 보기</h1>
    	
    	<p class="info">
    	이 글은 비밀글 입니다.<strong class="txt"> <br>비밀번호를 입력하여 주세요.</strong></p>
		
		<p class="passwd">
	    	<svg xmlns="http://www.w3.org/2000/svg" width="13" height="13" viewBox="0 0 24 24"><path d="M0 0h24v24H0z" fill="none"/><path d="M18 8h-1V6c0-2.76-2.24-5-5-5S7 3.24 7 6v2H6c-1.1 0-2 .9-2 2v10c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V10c0-1.1-.9-2-2-2zm-6 9c-1.1 0-2-.9-2-2s.9-2 2-2 2 .9 2 2-.9 2-2 2zm3.1-9H8.9V6c0-1.71 1.39-3.1 3.1-3.1 1.71 0 3.1 1.39 3.1 3.1v2z"/></svg><label for="board_pass">비밀번호</label>
	    	<input type="password" name="board_password" id="board_pass" placeholder="비밀번호" required>
    	</p>
    	
    	<div id="pass_btn">
	    	<input type="submit" value="확인">
	    	<input id="pass_cancel"type="button" value="취소">
    	</div>
    </div>
    
    </form>
    
	<%-- <%@include file="../include/footer.jsp"%> --%>

</body>
</html>
