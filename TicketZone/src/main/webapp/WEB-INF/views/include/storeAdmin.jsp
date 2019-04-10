<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Insert title here</title>
<style>
	#storeAdmin{
		display: inline-block;
		float:left;
		border: 2px solid;
		height: 600px;
		width: 10%;
	}
</style>
</head>
<body>
<div id="storeAdmin">
<ul>
	<li><a href="/updmStore_Register">매장 정보</a></li>
	<li><a href="/mTicketSet">번호표 설정</a></li>
	<li><a href="/mMenuAdmin">메뉴 관리</a></li>
</ul>
</div>
</body>
</html>