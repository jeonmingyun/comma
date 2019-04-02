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
<style>
#StoreBox div{
	position: relative;
	display: inline-block;
	width: 300px;
	height: 550px;
	margin: 0 auto;
	margin-top: 1%;
	margin-left: 1%;
}

#StoreBox2{
	position: relative;
	display: inline-block;
	width: 300px;
	height: 550px;
	margin: 0 auto;
	border: 1px solid;
	bottom: 280px;
	text-align: center;
}

.store_register{
	width: 80px;
	height: 50px;
	margin-top: 70%;
}

.license_number {
	width: 300px;
	height: 500px;
	border: 0px;
}

.s_img {
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>

	<c:if test="${empty sessionScope.id}">
		<button class="store_register">매장등록</button>
	</c:if>
	
	<div id="StoreBox">
		<c:if test="${!empty sessionScope.id}">
			<c:forEach var="s" items="${store}">
				<div class="mngrStore">
					<button class="license_number" value="${s.license_number}">
						<img class="s_img" src="resources/img/miyago.jpg">
					</button>
					<p>${s.store_name}</p>
				</div>
			</c:forEach>
			
			<div id="StoreBox2">
				<button class="store_register">매장등록</button>
			</div>
		</c:if>
	</div>
	
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>