<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- updmStore_Reg.js 경로 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- CSS경로 -->
<link href="resources/css/updmStore_Register.css" rel="stylesheet" />
<script src="resources/js/mngrOnly/updmStore_Reg.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a24234e20fd78957bd509e4c423610f&libraries=services"></script>


<title>등록한 매장 수정 페이지</title>
</head>
<body>
	<!-- 헤더부분 -->
	<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
	<%@include file="/WEB-INF/views/include/storeAdmin.jsp" %>
	<section>
		<div class="table_wapper">
			<form method="post" action="updmStore_Complete">
				<h1>매장 수정</h1>
				<input type="hidden" name="license_number"
					value="${updmStore.license_number}">
				<table class="mngr_show">
					<tbody>
						<tr>
							<th>매장이름</th>
							<td><input type="text" name="store_name"
								value="${updmStore.store_name}" required></td>
						</tr>
						<tr>
							<th>업종</th>
							<td><select name="cate_code">
									<c:forEach var="c" items="${cate}">
										<option value="${c.cate_code}">${c.cate_name}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th>대표자 이름</th>
							<td><input type="text" name="r_name"
								value="${updmStore.r_name}" required></td>
						</tr>
						<tr>
							<th>매장 전화번호</th>
							<td><input type="text" name="store_tel"
								value="${updmStore.store_tel}" required></td>
						</tr>
						<tr>
							<th>영업 시간</th>
							<td><input type="text" name="store_time"
								value="${updmStore.store_time}" required>
								<p>(ex:09:00-18:00 * 정확히입력하지않으면 데이터가들어가지않습니다.)</p></td>
						</tr>
						<tr>
							<th>매장 소개</th>
							<td><textarea name="store_intro" required>${updmStore.store_intro}</textarea></td>
						</tr>
						<tr>
							<th>매장 주소</th>
							<td><input type="text" id="sample5_address" name="address_name" placeholder="주소" value="${updmStore.address_name}">
								<input type="button" onclick="sample5_execDaumPostcode()"
								value="주소 검색"><br>
								<div id="map"
									style="width: 300px; height: 300px; margin-top: 10px; "></div></td>
							<c:forEach var="id" items="${id}">
								<input type="hidden" name="owner_id" value="${id.owner_id}">
							</c:forEach>
						</tr>
						
					</tbody>
				</table>
				<input type="hidden" id="coor_x" name="coor_x" value="${coor[0].coor_x}">
				<input type="hidden" id="coor_y" name="coor_y" value="${coor[0].coor_y}">
				<!-- 수정,삭제 버튼 -->

				<div class="write_btn">
					<input type="submit" id="update" value="수정">
					<input type="button" id="cancel" value="취소">
				</div>

			</form>
		</div>		
		
	</section>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>