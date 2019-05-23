<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>관리자 로그인</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="resources/js/AdminLogin.js"></script>
</head>
<body>
<h3>관리자 로그인 화면</h3>
<hr>
<div id="loginfail"></div>
<p>ID:<input type="text" id="admin_id" name="admin_id" size="20"></p>
<p>PASSWORD:<input type="password" id="admin_pass" name="admin_pass" size="20"></p>
<button id="Login_btn">Login</button>

</body>
</html>