<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function(){
		var issueForm = $("#issueForm");
		var successForm = $("#successForm");
		$("#issueForm button").on("click", function(e){
			e.preventDefault();
			issueForm.submit();
		});
		$("#successForm button").on("click", function(e){
			e.preventDefault();
			successForm.submit();
		});
	});
</script>
<title></title>
</head>
<body>
<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
<form id="issueForm" action="/issue_ticket" method="post">
	<input type="hidden" name="license_number" value="1001178800">
	<input type="hidden" name="the_number" value="2">
	<input type="hidden" name="member_tel" value="010-5555-0013">
	<button class="btn btn-default">발급</button>
</form>

<form id="successForm" action="/minus_ticket" method="post">
	<input type="hidden" name="license_number" value="1001178800">
	<button class="btn btn-default">결제완료</button>
</form> 
<form>
	<select id="storelist">
	<option>매장이름</option>
</select>
<h1>현재(오늘) 상태 통계</h1>
<table id="customAdmin">
	<tr>
		<th>대기자 수
		<th>부재
		<th>접수
		<th>접수 취소
	</th>
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
		<th>상태
	</th>
	<tr>
		<td>d
		<td>d
		<td>d
		<td>d
	</tr>
</table>
</form>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>





