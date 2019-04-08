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
<link href="resources/css/mngr.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="resources/js/mngr.js">
	
</script>
</head>
<body>

	<%@include file="include/header.jsp"%>
	<section>
		<div class="login_form">
			<h1>관리자 로그인</h1>
			
				<div id="loginfail"></div>
				<!-- <p>관리자 아이디</p> -->
				<input type="text" id="mngr_id" name="mngr_id" placeholder="관리자 아이디">
				<!-- <p>비밀번호</p> -->
				<input type="password" id="mngr_pw" name="mngr_pw"
					placeholder="비밀번호">

				<button id="login_btn" class="login_btn" name="login_btn">로그인</button>
				<button id="join_btn" class="login_btn" name="join_btn">관리자
					등록</button>
				<!-- <input type="button" name="login_btn" value="로그인">
                <input type="button" name="join_btn" value="관리자 등록">
                 -->
				<a href="/mngr_find">아이디 찾기</a> <a href="#">비밀번호 찾기</a>
			

		</div>
	</section>
	<%@include file="include/footer.jsp"%>

</body>
</html>
