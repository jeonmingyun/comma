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
	<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
	
	<div id="StoreBox">
		<c:if test="${!empty sessionScope.id}">
			<c:forEach var="s" items="${store}" varStatus="status">
				<div class="mngrStore">
					<button class="license_number" value="${s.license_number}">
						<img name="s_img${status.index}" class="s_img">
					</button>
					<p>${s.store_name}</p>
				</div>
				<input type="hidden" name="path" class="uuid${status.index}" value="${s.img_uuid}">
				<input type="hidden" name="path" class="filename${status.index}" value="${s.img_filename}">
				<input type="hidden" name="path"class="uploadpath${status.index}" value="${s.img_uploadpath}">
			</c:forEach>
			
			<div id="StoreBox2">
				<button class="store_register">매장등록</button>
			</div>
		</c:if>
	</div>

	<c:if test="${empty sessionScope.id}">
		<button class="store_register">매장등록</button>
	</c:if>
	
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>