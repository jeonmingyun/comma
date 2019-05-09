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

<script>
	$(document).ready(function(){
		$("#btnwrite").click(function()){
			// 페이지 주소 변경(이동)
			location.href = ;
		});
	});
</script>
</head>
<body>
<h2>게시글 목록</h2>
<table border="1" width="600px">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>이름</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
    <c:forEach var="row" items="{list}">
	<tr>	
		<td>번호1</td>
		<td>타이틀제목</td>
		<td>글쓴이</td>
		<td>2019.3.21
		
		</td>
		<td>3</td>
	</tr>
	</c:forEach>	
		
</table>
<button type="button" id="btnWrite">글쓰기</button>


	<%@include file="include/header.jsp" %>
	<p>고객센터</p>
	<%@include file="include/footer.jsp" %>

</body>
</html>
