<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="../resources/js/mngrOnly/mShowInquiry.js"></script>
<!-- <link href="../resources/css/showInquiry.css" rel="stylesheet" /> -->
<style media="screen">
</style>
<script>
	
</script>
</head>
<body>
	<h2>게시글 보기</h2>
	<table class="show">
		<tbody>
			<tr>
				<th scope="row">제목</th>
				<td>${InquiryUpd[0].board_title}</td>
			</tr>
			<tr>
				<th scope="row">내용</th>
				<td>${InquiryUpd[0].board_content}</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td></td>
			</tr>
		</tbody>
	</table>
	<button type="button" id="btnUpdate"
		onclick="updInquiry(${InquiryUpd[0].board_no})">수정</button>
	<button type="button" id="btnDelete"
		onclick="delInquiry(${InquiryUpd[0].board_no})">삭제</button>
	
	 <%@include file="../../include/reply.jsp"%> 
</body>
</html>