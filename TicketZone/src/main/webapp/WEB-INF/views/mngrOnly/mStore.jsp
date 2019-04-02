<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>관리자 로그인후 첫화면</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="resources/js/mngrOnly/mStore.js"></script>
</head>
<body>
<c:if test="${empty sessionScope.id}">
	<button class="store_register">매장등록</button>
</c:if>
<c:if test="${!empty sessionScope.id}">
	<c:forEach var="o" items="${id}">
		${o.owner_id}님 환영합니다.
	</c:forEach>
	<br><button class="store_register">매장등록</button>
</c:if>
</body>
</html>