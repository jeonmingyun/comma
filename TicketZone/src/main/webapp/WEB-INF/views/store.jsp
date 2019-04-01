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
<link href="resources/css/store.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="resources/js/store.js"></script>
</head>
<body>

	<div id="area_form">
		<%@include file="include/header.jsp"%>

		<section>
			<div id="search_box">
				<!-- 지역 -->
				<select id="area">
					<option value="hide">지역선택</option>
					<option value="seoul">서울</option>
					<option value="busan">부산</option>
					<option value="daegu">대구</option>
					<option value="gwangju">광주</option>
					<option value="incheon">인천</option>
					<option value="daejeon">대전</option>
					<option value="ulsan">울산</option>
					<option value="gyunggi">경기</option>
					<option value="gangwon">강원</option>
					<option value="chungnam">충남</option>
					<option value="chungbuk">충북</option>
					<option value="gyeongnam">경남</option>
					<option value="gyeongbuk">경북</option>
					<option value="jeonnam">전남</option>
					<option value="jeonbuk">전북</option>
					<option value="jeju">제주</option>
				</select>
				<!-- 시/군/구 -->
				<select id="city">
					<option value="hide">시/군/구</option>
					<option value="seoul">서울</option>
					<option value="busan">부산</option>
					<option value="daegu">대구</option>
					<option value="gwangju">광주</option>
					<option value="incheon">인천</option>
					<option value="daejeon">대전</option>
					<option value="ulsan">울산</option>
					<option value="gyunggi">경기</option>
					<option value="gangwon">강원</option>
					<option value="chungnam">충남</option>
					<option value="chungbuk">충북</option>
					<option value="gyeongnam">경남</option>
					<option value="gyeongbuk">경북</option>
					<option value="jeonnam">전남</option>
					<option value="jeonbuk">전북</option>
					<option value="jeju">제주</option>
				</select>
				<!-- 검색 -->
				<input type="search" name="search" placeholder="매장이름">
				<input type="submit" value="검색" class="search_btn">
			</div>
			<!--search_box-->

			<!-- 제휴매장 -->
			<div id="store_form">
				<ul class="store_img">
					<c:forEach var="store" items="${list}">
						<li>
							<button class="license" value="${store.license_number}">
								<img class="s_img" src="resources/img/miyago.jpg">
							</button>
							<div>
								<h3>${store.store_name}</h3>
								<p>매장연락처: ${store.store_tel}</p>
								<p>매장주소: ${store.address_name}</p>
								<p>영업시간: ${store.store_time}</p>
							</div>
						</li>
					</c:forEach>
				</ul>

				<!-- pagination -->
				<div class="pagination">
					<a href="#"><</a> 
					<a href="#" class="active"> 1 </a> 
					<a href="#"> 2 </a> 
					<a href="#"> 3 </a> 
					<a href="#">></a>
				</div>
			</div>
		</section>

		<%@include file="include/footer.jsp"%>
	</div>

</body>
</html>

