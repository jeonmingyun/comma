<<<<<<< HEAD
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
<link href="resources/CSS/home.css" rel="stylesheet"/>

</head>
<body>

	<%@include file="include/header.jsp"%>
	<p>HOME</p>
	
	<h1>공지사항</h1>
	<table id="notice">
			<tr>
				<td>번호</td>
				<td>내용</td>
				<td>작성자</td>
				<td>날짜</td>
			</tr>
		<c:forEach var="list" items="">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</c:forEach>
	</table>

	<h1>번호요 지금 바로 이용하기</h1>
	<img alt="no image" src="resources/img/playstore.png">
	<%@include file="include/footer.jsp"%>

</body>
</html>

