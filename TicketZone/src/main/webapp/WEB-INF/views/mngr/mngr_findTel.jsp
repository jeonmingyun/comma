<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>관리자 아이디 찾기</title>
<link href="../resources/css/mngr_findTel.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<section>
<div class="find_form">
	<h1>관리자 아이디 찾기</h1>
	<form action="/mngr" method="post">
	<div id="id">
	<p id="id_find">회원님의 아이디는 : ${tel[0].owner_id}</p>
	<input type="submit" class="log_inPage" name="log_inPage" value="로그인하기">
	</div>
	</form>
</div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>