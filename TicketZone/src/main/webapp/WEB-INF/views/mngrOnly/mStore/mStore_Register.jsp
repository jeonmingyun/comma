<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="../resources/css/mStore_Register.css" rel="stylesheet" />
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a24234e20fd78957bd509e4c423610f&libraries=services"></script>

<title>매장등록 페이지</title>
</head>
<body>
	<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
	<section>

		<form id="insert_Store" method="post" action="/mStore_Reg">
			<div class="table_wapper">
				<h1>매장 등록</h1>
				<table class="mngr_show">
					<tbody>
						<tr>
							<th><label for="store_name">매장이름</label></th>
							<td><input type="text" name="store_name" id="store_name"></td>
						</tr>
						<tr>
							<th><label for="cate">업종</label></th>
							<td><select name="cate_code" id="cate">
									<c:forEach var="c" items="${cate}">
										<option value="${c.cate_code}">${c.cate_name}</option>
									</c:forEach>
							</select></td>
						<tr>
							<th><label for="r_name">대표자 이름</label></th>
							<td><input type="text" name="r_name" id="r_name"></td>
						</tr>
						<tr>
							<th><label for="license_number">사업자 번호</label></th>
							<td><input type="text" name="license_number" id="license_number"></td>
						</tr>
						<tr>
							<th><label for="store_tel">매장 전화번호</label></th>
							<td><input type="text" name="store_tel" id="store_tel"></td>
						</tr>
						<tr>
							<th>영업 시간</th>
							<td><input type="time" id="time" name="aaa"> - <input
								type="time" id="time2" name="bbb"></td>
						</tr>
						<tr>
							<th><label for="store_intro">매장 소개</label></th>
							<td><textarea name="store_intro" id="store_intro"></textarea></td>
						</tr>
						<tr>
							<th>매장 주소</th>
							<td><input type="text" id="sample5_address"
								name="address_name" placeholder="주소"> 
								<input type="button" onclick="sample5_execDaumPostcode()"
								id="address_search" value="검색"><br>
								<div id="map"
									style="width: 300px; height: 300px; margin-top: 10px; display: none"></div>
							</td>
						</tr>
						<tr>
							<th>매장사진</th>
							<td><input id="upload_img" type="file" name="uploadFile">
								<div class="img_preview">
									<img id="preview"
										style="width: 300px; height: 300px; margin-top: 10px; display: none">
								</div>
								<div class="resultDiv"></div></td>
						</tr>
						<c:forEach var="id" items="${id}">
							<input type="hidden" name="owner_id" value="${id.owner_id}">
						</c:forEach>

					</tbody>
				</table>
				<input type="hidden" id="coor_x" name="coor_x"> <input
					type="hidden" id="coor_y" name="coor_y">
				<!-- 등록 버튼 -->
				<div id="regi_button">
					<input type="submit" id="regi" value="등록">
				</div>
				<!-- <button id="test">test</button> -->
			</div>
		</form>

		<!-- table_wapper -->

		<!-- 지도 -->
		<script src="../resources/js/mngrOnly/mStore_Register.js"></script>
	</section>
	<%-- <%@include file="/WEB-INF/views/include/footer.jsp"%> --%>
</body>
</html>