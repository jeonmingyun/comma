<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>관리자 비밀번호 찾기</title>
<link href="../resources/css/mngr_passwd.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="resources/js/mngr_find.js"></script>

</head>
<body>
<%@include file="/WEB-INF/views/include/header.jsp"%>
	<div class="find_form">
		<h2>관리자 비밀번호 찾기</h2>
		<div class="divider_1"><div class="line"></div></div>
		
		<div id="divider_sh">
			<div class="line"></div>
		</div>
		
		<div id="passwd_box">
			<!-- <input type="text" id="owner_id" name="owner_id" required placeholder="아이디">
			<button>확인</button> -->
			<input type="email" id="email" name="email" required placeholder="이메일">
			<button>전송</button>
		</div>
	
	</div>
</body>
</html>