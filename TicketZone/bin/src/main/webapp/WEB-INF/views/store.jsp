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
<link href="../resources/CSS/store.css" rel="stylesheet">
</head>
<body>

	<%@include file="include/header.jsp"%>

	<section>
		<div class="search_form">
			<div class="search_box">
				<!-- 지역 -->
				<li><select id="area">
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
				</select> <!-- 시/군/구 --> <select id="city">
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
				</select> <!-- 검색 --> <input type="search" name="search" placeholder="매장이름">
					<input type="submit" value="검색" class="search_btn"></li>
			</div>
			<!--search_box-->
		</div>
		<!--search_form-->

		<!-- 제휴매장 -->

		<div>
			<img src="../resources/img/test.png">
			<h3>서울식당</h3>

			<p>평일: 09:00 ~ 20:00</p>
			<p>주말: 10:00 ~ 21:00</p>
			<p>매장주소: 대구 북구 동북로49</p>
			<p>연락처: 010-1234-4567</p>
		</div>
		<div>
			<img src="../resources/img/test.png">
			<h3>서울식당</h3>

			<p>평일: 09:00 ~ 20:00</p>
			<p>주말: 10:00 ~ 21:00</p>
			<p>매장주소: 대구 북구 동북로49</p>
			<p>연락처: 010-1234-4567</p>
		</div>
		<div>
			<img src="../resources/img/test.png">
			<h3>서울식당</h3>

			<p>평일: 09:00 ~ 20:00</p>
			<p>주말: 10:00 ~ 21:00</p>
			<p>매장주소: 대구 북구 동북로49</p>
			<p>연락처: 010-1234-4567</p>
		</div>
	</section>



	<%@include file="include/footer.jsp"%>

</body>
</html>
