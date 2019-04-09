<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title></title>
<link href="resources/css/header.css" rel="stylesheet"/>
<script>
	$(document).ready(function(){
		$("#log_out").click(function(){
			window.location.href="/mngrLogout";
		});
		
		$("#mainLogo").click(function(){
			window.location.href="/mngrOnly";
		})
	});
</script>
</head>
<body>
	<header>
		<!-- 로고 -->
		<img id="mainLogo" src="resources/img/mainLogo.png">

		<ul class="main-nav">
			<li><a href="/mngrOnly">홈</a></li>
			<li><a href="/mState">매장관리</a></li>
			<li><a href="/mCustomer?license_number=${store[0].license_number}">고객관리</a></li>
			<li><a href="/mInquiry">건의사항</a></li>
		</ul>
		
		<!-- 로그아웃 -->
		<button id="log_out">로그아웃</button>
	</header>

</body>
</html>
