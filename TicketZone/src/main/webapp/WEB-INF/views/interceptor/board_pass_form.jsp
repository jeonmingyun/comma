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

</head>
<body>

	<%@include file="../include/header.jsp"%>
	
    <form action="/board_pass_pro" method="post">
    	<input type="password" name="board_pass" placeholder="비밀번호" required>
    	<input type="submit" value="확인">
    	<input type="button" value="취소">
    </form>
    
	<%@include file="../include/footer.jsp"%>

</body>
</html>
