<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>메뉴 관리</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="resources/css/mMenu.css" rel="stylesheet">
<script src="resources/js/mngrOnly/mMenu.js"></script>
</head>

<body>
<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
<%@include file="/WEB-INF/views/include/side.jsp" %>


<div id="menu">
<h1>메뉴 리스트 보기</h1>

	<div id="menuTab">
		<div class="menu_box">	
		<p>분류</p>
		<c:if test="${empty tab}"></c:if>		
		<c:if test="${!empty tab}">
			<c:forEach var="c" items="${tab}" varStatus = "status">
				<p class="Tab"><input type="checkbox" class="categorie" value="${status.index}" style="display:none;"><button class="catebtn" name="menu_cate" value="${c.menu_cate}">${c.menu_cate}</button>
				</p>
			</c:forEach>
		</c:if>	
			<c:if test="${!empty add}">
				<p class="Tab"><button class="addcate" name="menu_cate" value="${add}">${add}</button></p>
			</c:if>
		</div>	
			<div>
				<p>리스트 추가하기:<input type="text" id="setMenu" name="setMenu">
				<button id="addSetMenu">분류 추가하기</button>
			</div>					
	</div>

		<!-- <div id="menuList" style="display:inline-block; text-align:center; float:left;"> -->
		<div id="menuList">
			<button id="CateInputUpdate">탭 수정하기</button>
			<button id="addMenu">메뉴 추가하기</button>
			<button id="UpdateInputMenu">수정</button>
			<button id="DeleteMenu">삭제</button>	
		<c:if test="${empty menu}">
			<table id="customAdmin" class="custom_table">
				<tr>
					<th width="50px"><input type="checkbox" id="selectAll"></th>
					<th width="250px">메뉴이름</th>
					<th width="50px">가격</th>
					<th width="450px" align="left">설명</th>
				</tr>
			</table>	
		</c:if>
		<c:if test="${!empty menu}">
			<table id="customAdmin" class="custom_table">
				<tr>
					<th width="50px"><input type="checkbox" id="selectAll"></th>
					<th width="250px">메뉴이름</th>
					<th width="50px">가격</th>
					<th width="450px" align="left">설명</th>
				</tr>
				<c:forEach var="m" items="${menu}">
				<tr>
					<td><input type="checkbox" class="selectMenu" name="selectMenu" value="${m.menu_code}">
					<td id="menu_name">${m.menu_name}
					<td id="menu_price">${m.menu_price}
					<td id="store_note">${m.store_note}
				</tr>
				</c:forEach>				
			</table>
		</c:if>
			
			<button id="TraddMenu" style="display:none">추가하기</button>		
			<button id="UpdateMenu" style="display:none">수정하기</button>
			<button id="UpdateCate" style="display:none">탭 수정하기</button>
	</div>		 
</div>


<c:if test="${!empty param.license_number}">			
<input type="hidden" name="license_number" value="${param.license_number}">
</c:if>
<c:if test="${empty param.license_number}">			
<input type="hidden" name="license_number" value="${tab[0].license_number}">
</c:if>
<c:if test="${!empty param.menu_cate}">
<input type="hidden" name="menu_cate" value="${param.menu_cate}">
</c:if>
<c:if test="${empty param.menu_cate}">
<input type="hidden" name="menu_cate" value="${tab[0].menu_cate}">
</c:if>

<%-- <%@include file="/WEB-INF/views/include/footer.jsp"%> --%>
</body>
</html>