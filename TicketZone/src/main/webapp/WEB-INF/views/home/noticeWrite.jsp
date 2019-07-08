<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>공지사항</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="resources/js/NoticeWrite.js"></script>
<link href="resources/css/noticeWrite.css" rel="stylesheet" />
</head>
<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<form method="post" id="insert_from" action="/noticeInsert">
		<div class="table_wapper">
			<h1>공지사항 작성</h1>
			<table class="notice_show">
				<tbody>
					<tr>
						<th><label for="notice_title">제목</label></th>
						<td><input type="text" id="notice_title" name="notice_title"></td>
					</tr>

					<tr>
						<th><label for="notice_content">내용</label></th>
						<td><textarea id="notice_content" name="notice_content"></textarea></td>
					</tr>

					<tr>
						<th><label for="upload_file">첨부파일</label></th>
						<td><input type="file" name="uploadFile" multiple>
							<div class="resultDiv"></div>
					</tr>
				</tbody>
			</table>

			<!-- 확인 취소버튼 -->
			<div class="write_btn">
				<input type="submit" value="등록">
				<button type="button" id="write_cancle">취소</button>
			</div>
		</div>
		<!-- table_wapper -->
	</form>
</body>
</html>