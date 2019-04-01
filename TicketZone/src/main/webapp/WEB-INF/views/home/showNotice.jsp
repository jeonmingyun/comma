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
				<th>첨부파일</th>
				<td></td>
			</tr>
		</tbody>
	</table>
	<button type="button" id="btnUpdate"
		onclick="updNotice(${noticeUpd[0].notice_no})">수정</button>
	<button type="button" id="btnDelete"
		onclick="delNotice(${noticeUpd[0].notice_no})">삭제</button>

	<div class="container">
		<form id="commentForm" name="commentForm" method="post">
			<br> <br>
			<div>
				<div>
					<span><strong>Comments</strong></span>
				</div>
				<div>
					<table class="table">
						<tr>
							<td><textarea id="comment" name="comment" placeholder="댓글입력"></textarea>
								<br></td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
	<div class="container">
		<form id="commentListForm" name="commentListForm" method="post">
			댓글 리스트
			<div id="commentList"></div>
		</form>
	</div>
	<div>
		<button type="button" id="cmtRegist">등록</button>
	</div>
</body>
</html>