<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>문의글 수정하기</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="../resources/js/mngrOnly/mUpdInquiry.js"></script>
<link href="../resources/css/inquiryWrite.css" rel="stylesheet" />

<script>
	
</script>
</head>

<body>
	<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
	<div class="table_wapper">
	<h1>문의글 수정</h1>
	
	<!-- <form method="post" action="/mUpdInquiryForm"> -->
		<input type="hidden" value="${InquiryUpd[0].board_no}" name="board_no">
		
		<table class="cus_show">
			<tbody>
				<tr>
					<th><label for="suggest_type">문의유형</label></th>
					<td></td>
				</tr>
				
				<tr>
					<th><label for="title">제목</label></th>
					<td>
						<input name="board_title" id="title" size="80" value="${InquiryUpd[0].board_title}">
					</td>
				</tr>
				
				<tr>
					<th><label for="content">내용</label>
					<td>
						<textarea name="board_content" id="content" rows="4" cols="80">${InquiryUpd[0].board_content}</textarea>
					</td>				
				</tr>
				
				<tr>
					<th><label for="uploadFile" id="date">첨부파일</label></th>
					<td>
						<div id="uploadFile">
							<c:if test="${file[0] != null}"><a href="#">${file[0].inq_filename}</a>
							<button id="deleteFile">삭제</button></c:if>
							<c:if test="${file[0] == null}"></c:if>
						</div>
						<input type="file" name="uploadFile">
					</td>
				</tr>
			</tbody>
		</table>
		
		<div class="write_btn">
			<input type="submit" id="update" value="수정">
			<input type="button" id="cancel" value="취소">
		</div>
	</div>
		
		<div class="modifyDiv"></div>
		<c:if test="${file[0] != null}">
		<input id="inq_uuid" type="hidden" name="inq_uuid" value="${file[0].inq_uuid}">
		<input id="inq_uploadpath" type="hidden" name="inq_uploadpath" value="${file[0].inq_uploadpath}">
		<input id="inq_filename" type="hidden" name="inq_filename" value="${file[0].inq_filename}">		
		</c:if>
	<!-- </form> -->
</body>
</html>