<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Insert title here</title>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a24234e20fd78957bd509e4c423610f&libraries=services"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="../resources/js/store_info.js"></script>
<link href="../resources/css/store_info.css" rel="stylesheet">
</head>
<body>
	<!-- 가게 정보 -->
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	
	<div id="store_name">${storeList[0].store_name}</div>
	
	<c:forEach var="s" items="${storeList}">
		<div id="store_info" name="${s.address_name}">
			<p>
				<img src="../resources/img/miyago.jpg">
			</p>
			<p id="store_name">매장이름: ${s.store_name}</p>
			<p>매장연락처: ${s.store_tel}</p>
			<p>매장주소: ${s.address_name}</p>
			<p>영업시간: ${s.store_time}</p>
			<p>매장소개: ${s.store_intro}</p>
		</div>
	</c:forEach>

	<!-- 지도 -->
	<div id="map"></div>

	<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>