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
<link href="resources/css/mStore.css" rel="stylesheet" />
</head>
<body>
<div class="store_container">
	<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
	<h2>매장 등록</h2>
	<div class="divider_1"><div class="line"></div></div>
	
	<div id="StoreBox">
		<c:if test="${!empty sessionScope.id}">
			<c:forEach var="s" items="${store}" varStatus="status">
				<div class="mngrStore">
					<button class="license_number" value="${s.license_number}">
						<img name="s_img${status.index}" class="s_img">
					</button>
					<h3>${s.store_name}</h3>
				</div>
				<input type="hidden" name="path" class="uuid${status.index}" value="${s.img_uuid}">
				<input type="hidden" name="path" class="filename${status.index}" value="${s.img_filename}">
				<input type="hidden" name="path"class="uploadpath${status.index}" value="${s.img_uploadpath}">
			</c:forEach>
			
			<!-- <div id="StoreBox2" class="mngrStore">
				<button class="store_register">
					<img src="../resources/icon/plus.png">
				</button>
			</div> -->
			
			<div id="StoreBox2" class="mngrStore">
				<img class="store_register" src="../resources/icon/plus.png">
			</div>
			
		</c:if>
	</div>

	<c:if test="${empty sessionScope.id}">
		<button class="store_register">매장등록</button>
	</c:if>
</div><!-- store_container -->
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>