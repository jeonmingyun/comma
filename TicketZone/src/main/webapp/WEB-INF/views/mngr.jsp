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
<script src="resources/js/mngr.js"></script>
</head>
<body>

	<%@include file="include/header.jsp"%>
	<section>
		<div class="login_form">
			<h2>관리자 로그인</h2>
			<div class="divider_1"><div class="line"></div></div>
				
				<!-- <div id="loginfail"></div> -->
				<!-- <p>관리자 아이디</p> -->

			<div id="divider_sh">
				<div class="line"></div>
			</div>
			
			<div class="login_box">
				<div class="cont_join">
					<h4>관리자 등록
						<span>관리자 등록을 하셔야 매장등록이 가능합니다.</span>
					</h4>
					<p><button id="join_btn" class="login_btn" name="join_btn">관리자
							등록</button>
				</div>
				
				<div class="cont_login">
					<h4>관리자 로그인</h4>
						<ul>
							<li><input type="text" id="mngr_id" name="mngr_id" placeholder="관리자 아이디" />
							<li><input type="password" id="mngr_pw" name="mngr_pw"
								placeholder="비밀번호" />
						</ul>
						
						<p><button id="login_btn" class="login_btn" name="login_btn">로그인</button>
						<p><a href="/mngr_find">아이디 찾기</a> <a href="#">비밀번호 찾기</a>
				</div>
					
					<!-- <input type="button" name="login_btn" value="로그인">
	                <input type="button" name="join_btn" value="관리자 등록">
	                 -->		
			</div> <!-- login_box -->
		</div> <!-- login_form -->
	</section>
	<%-- <%@include file="include/footer.jsp"%> --%>

</body>
</html>
