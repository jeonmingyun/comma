<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>메뉴 관리</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="../resources/css/store_menu.css" rel="stylesheet">
<script src="../resources/js/store_menu.js"></script>
</head>

<body>

	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<input type="hidden" id="s_name" value="${param.store_name}">
	<div class="store_form">
		<div id="tab_menu">
			<h2 id="store_name">${param.store_name}</h2>
			<nav>
				<ul class="menu">
					<li><button id="goInfo">매장정보</button></li>
					<li><button id="goChart">통계</button></li>
					<li><button id="goMenu">메뉴</button></li>
				</ul>
			</nav>
		</div>
		<div class="divider_sh">
			<div class="line"></div>
		</div>
		<div class="left">
			<div id="cate">
				<div class="cate_text">
					<h5>카테고리</h5>
					<c:if test="${empty tab}">
						<ul>
							<li></li>
						</ul>
					</c:if>

					<c:if test="${!empty tab}">
						<c:forEach var="c" items="${tab}" varStatus="status">
							<ul>
								<li>
									<button class="catebtn" name="menu_cate" value="${c.menu_cate}">${c.menu_cate}</button>
								</li>
							</ul>
						</c:forEach>
					</c:if>

					<c:if test="${!empty add}">
						<p class="Tab">
							<button class="addcate" name="menu_cate" value="${add}">${add}</button>
						</p>
					</c:if>
				</div>
				<!-- cate_text -->

			</div>
			<!-- cate -->
		</div>
		<!-- left -->

		<!-- 메뉴표 -->
		<div class="right">
			<!-- menu_btn -->

			<c:if test="${empty menu}">
				<table id="customAdmin">
					<tbody>
						<tr>
							<!-- <th width="50px"><input type="checkbox" id="selectAll"></th> -->
							<th width="250px">메뉴 이름</th>
							<th width="50px;">가격</th>
							<th width="450px;">설명</th>
						</tr>

						<tr>
							<!-- <td></td> -->
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</c:if>

			<c:if test="${!empty menu}">
				<table id="customAdmin">
					<tbody>
						<tr>
							<!-- <th width="50px"><input type="checkbox" id="selectAll"></th> -->
							<th width="250px">메뉴 이름</th>
							<th width="50px;">가격</th>
							<th width="450px;">설명</th>
						</tr>
						<c:forEach var="m" items="${menu}">
							<tr>
								<%-- <td><input type="checkbox" class="selectMenu" name="selectMenu" value="${m.menu_code}"></td> --%>
								<td id="menu_name">${m.menu_name}</td>
								<td id="menu_price">${m.menu_price}</td>
								<td id="store_note">${m.store_note}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>



		</div>
		<!-- right -->

		<c:if test="${!empty param.license_number}">
			<input type="hidden" id="license_number" name="license_number"
				value="${param.license_number}">
		</c:if>
		<c:if test="${empty param.license_number}">
			<input type="hidden" name="license_number"
				value="${tab[0].license_number}">
		</c:if>
		<c:if test="${!empty param.menu_cate}">
			<input type="hidden" name="menu_cate" value="${param.menu_cate}">
		</c:if>
		<c:if test="${empty param.menu_cate}">
			<input type="hidden" name="menu_cate" value="${tab[0].menu_cate}">
		</c:if>

	</div>
	<!-- store_form -->

</body>
</html>