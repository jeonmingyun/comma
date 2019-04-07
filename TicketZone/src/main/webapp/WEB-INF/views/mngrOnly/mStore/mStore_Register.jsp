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
<script
	src="http://dmaps.daum.net/map_js_init/postcode.v2.js?autoload=false"></script>
<script
	src="http://dmaps.daum.net/map_js_init/postcode.v2.js?autoload=false"></script>
<script>
	function sample6_execDaumPostcode() {
		daum.postcode
				.load(function() {
					new daum.Postcode(
							{
								oncomplete : function(data) {
									var addr = '';
									if (data.userSelectedType === 'R') {
										addr = data.roadAddress;
									} else {
										addr = data.jibunAddress;
									}
									document.getElementById('sample6_postcode').value = data.zonecode;
									document.getElementById("sample6_address").value = addr;
								}
							}).open();
				});
	}
</script>
<title>매장등록 페이지</title>
</head>
<body>
	<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
	<section>
		<div class="table_wapper">
			<form method="post" action="/mStore_Reg">
				<h1>매장 등록</h1>
				<table class="mngr_show">
					<tbody>
						<tr>
							<th>매장이름</th>
							<td><input type="text" name="store_name"></td>
						</tr>
						<tr>
							<th>업종</th>
							<td><select name="cate_code">
									<c:forEach var="c" items="${cate}">
										<option value="${c.cate_code}">${c.cate_name}</option>
									</c:forEach>
							</select></td>
						<tr>
							<th>대표자 이름</th>
							<td><input type="text" name="r_name"></td>
						</tr>
						<tr>
							<th>사업자 번호</th>
							<td><input type="text" name="license_number"></td>
						</tr>
						<tr>
							<th>매장 전화번호</th>
							<td><input type="text" name="store_tel"></td>
						</tr>
						<tr>
							<th>영업 시간</th>
							<td><input type="text" name="store_time">
								<p>(ex:09:00-18:00 * 정확히입력하지않으면 데이터가들어가지않습니다.)</p></td>
						</tr>
						<tr>
							<th>매장 소개</th>
							<td><textarea name="store_intro"></textarea></td>
						</tr>
						<tr>
							<th>매장 주소</th>
							<td><input type="text" name="address_code"
								id="sample6_postcode" placeholder="우편번호">-<input
								type="text" id="sample6_address" name="address_name"
								placeholder="주소"><input type="button"
								onclick="sample6_execDaumPostcode()" value="우편번호 찾기"></td>

							<c:forEach var="id" items="${id}">
								<input type="hidden" name="owner_id" value="${id.owner_id}">
							</c:forEach>
						</tr>
					</tbody>
				</table>
				<!-- 등록 버튼 -->
				<input type="submit" value="등록">
			</form>
		</div> <!-- table_wapper -->
	</section>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>