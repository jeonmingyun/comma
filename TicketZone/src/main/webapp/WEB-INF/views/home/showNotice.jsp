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
<script src="../resources/js/showNotice.js"></script>
<link href="../resources/css/showNotice.css" rel="stylesheet" />
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
				<td>${noticeUpd[0].notice_title}</td>
			</tr>
			<tr>
				<th scope="row">작성자</th>
				<td>관리자</td>
			</tr>
			<tr>
				<th scope="row">내용</th>
				<td>${noticeUpd[0].notice_content}</td>
			</tr>
			<tr>
				<th scope="row">첨부파일</th>
				<td><c:if test="${file[0] != null}">${file[0].fileName}</c:if><c:if test="${file[0] == null}">없음</c:if></td>
			</tr>
		</tbody>
	</table>
	<c:if test="${file[0] != null}">
		<div class="uploadResult">
			<ul>
				
			</ul>
		</div>
	</c:if>	
		<c:if test="${file[0] != null}">
		<input id="uuid" type="hidden" name="uuid" value="${file[0].uuid}">
		<input id="fileType" type="hidden" name="fileType" value="${file[0].fileType}">
		<input id="uploadPath" type="hidden" name="uploadPath" value="${file[0].uploadPath}">
		<input id="fileName" type="hidden" name="fileName" value="${file[0].fileName}">
		</c:if>		
	<button type="button" id="btnUpdate"
		onclick="updNotice(${noticeUpd[0].notice_no})">수정</button>
	<button type="button" id="btnDelete"
		onclick="delNotice(${noticeUpd[0].notice_no})">삭제</button>

</body>
</html>