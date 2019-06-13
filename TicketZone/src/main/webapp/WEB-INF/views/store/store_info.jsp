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
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a24234e20fd78957bd509e4c423610f&libraries=services,clusterer,drawing"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="../resources/js/store_info.js"></script>
<link href="../resources/css/store_info.css" rel="stylesheet">
</head>
<body>
	<!-- 가게 정보 -->
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<section>

		<div id="store_name" style="display: none;">${storeList[0].store_name}</div>
		
		<c:forEach var="s" items="${storeList}">
			<div id="store_info" class="${s.address_name} store_row">

				<div id="tab_menu">
					<h2 id="store_name">${s.store_name}</h2>
					<nav>
						<ul class="menu">
							<li><button id="goMenu">메뉴</button></li>
							<li><button id="goChart">통계</button></li>
							<li><button id="goInfo">매장정보</button></li>
						</ul>
					</nav>
				</div>
				
				<div class="divider_sh">
					<div class="line"></div>
				</div>

				<div class="left">
					<img name="store_img">
				</div>

				<div class="right">
				<dl>
					<dt>연락처
					<dd>${s.store_tel}
					
					<dt>매장주소
					<dd>${s.address_name}
					
					<dt>영업시간
					<dd>${s.store_time}
					
					<dt>매장소개
					<dd>${s.store_intro}
				</dl>
				</div>
			</div>
			<input type="hidden" id="s_name" value="${storeList[0].store_name}">
			<input type="hidden" id="license_number" value="${storeList[0].license_number}">
			<input type="hidden" id="uuid" value="${s.img_uuid}">
			<input type="hidden" id="uploadpath" value="${s.img_uploadpath}">
			<input type="hidden" id="filename" value="${s.img_filename}">
		</c:forEach>


		<!-- 지도 -->
		<div class="store_row">
			<h3>매장 오시는 길</h3>
			<div id="map"></div>
		</div>
		
		
	</section>
	
	<!-- store_form -->

	<%@include file="/WEB-INF/views/include/footer.jsp"%>

</body>
</html>