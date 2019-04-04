<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="resources/css/mCustomer.css" rel="stylesheet" />
<script src="resources/js/mngrOnly/mCustomer.js"></script>
<title></title>
</head>
<body>
	<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
	
	<form id="issueForm" action="/issue_ticket" method="post">
		<input type="hidden" name="license_number" value="${param.license_number}">
		<input type="hidden" name="the_number" value="2"> <input
			type="hidden" name="member_tel" value="010-5555-0013">
		<button class="btn btn-default">발급</button>
	</form>
	<input type="hidden" id="check" value="${id}">
	<form id="successForm" action="/minus_ticket" method="post">
		<input type="hidden" name="license_number" value="${param.license_number}">
		<button class="btn btn-default">결제완료</button>
	</form>
	
	<input type="hidden" id="select_license_number" value="${param.license_number}">	
	<select name="license_number" id="storelist">
		<c:forEach var="s" items="${store}" varStatus="status">
			<option class="storeList_option" value="${s.license_number}">
				${s.store_name}</option>
		</c:forEach>
	</select>

	<h1>현재(오늘) 상태 통계</h1>
	<table id="customAdmin">
		<tr>
			<th>대기자 수
			<th>부재
			<th>접수
			<th>접수 취소</th>
		<tr>
			<td>d
			<td>d
			<td>d
			<td>d
		</tr>
	</table>

	<h1>고객 대기 목록</h1>
	<table id="customAdmin">
		<tr>
			<th>번호
			<th>고객
			<th>인원 수
			<th>상태</th>

			<c:forEach var="m" items="${license_number}">
				<tr>
					<td>${m.ticket_code}
					<td>${m.member_tel}
					<td>${m.the_number}
					<td>${m.ticket_status}
				</tr>
			</c:forEach>
	</table>
	<div></div>

	<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>





