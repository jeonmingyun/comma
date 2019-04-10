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
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
	#menu{
		display:inline-block;
	}
	
	#Leaflet{
		display:inline-block;
	}
	
	#Leaflet_img{
		width: 100px;
		height: 100px; 
	}
</style>
</head>
<body>
<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
<%@include file="/WEB-INF/views/include/storeAdmin.jsp" %>
<section>
<div id="menu">
<h2>메뉴 리스트 보기</h2>
<select>
	<option>기본 메뉴</option>
	<option>사이드 메뉴</option>
	<option>공기밥</option>
	<option>주스</option>
</select><br>

<select>
	<option>세트 메뉴</option>
	<option>돈가스 세트</option>
	<option>햄버거 세트</option>
	<option>짜장면 세트</option>
</select>
</div>
<div id="Leaflet">
	<h2>전단지</h2>
	<img id="Leaflet_img" src="resources/img/miyago.jpg">
</div>
	<button>수정</button>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>