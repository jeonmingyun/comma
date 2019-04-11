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
	<div class="table_wapper">
		<h1>게시글 보기</h1>
		<table class="notice_show">
			<tbody>
				<tr>
					<!-- <th scope="row">제목</th> -->
					<td><h2>${noticeUpd[0].notice_title}</h2></td>
				</tr>
				<tr>
					<!-- <th scope="row">작성자</th> -->
					<td>관리자</td>


					<!-- <th scope="row">작성일</th> -->
					<td>${noticeUpd[0].notice_reg }</td>
				</tr>
				<tr>
					<th scope="row">내용</th>
					<td>${noticeUpd[0].notice_content}</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td></td>
				</tr>
			</tbody>
		</table>
		<button type="button" id="btnUpdate"
			onclick="updNotice(${noticeUpd[0].notice_no})">수정</button>
		<button type="button" id="btnDelete"
			onclick="delNotice(${noticeUpd[0].notice_no})">삭제</button>
	</div>
</body>
</html>