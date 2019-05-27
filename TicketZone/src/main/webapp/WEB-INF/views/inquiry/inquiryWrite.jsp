<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>고객센터 게시글 작성</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="../resources/css/inquiryWrite.css" rel="stylesheet" />
<script src="../resources/js/inquiryWrite.js"></script>

</head>
<body>
	<%@include file="../include/header.jsp"%>
	<!-- 고객센터 문의글 작성 -->
	 <form name="suggest" id="suggest_form" method="post" action="/insertInquiry"> 
		<div class="table_wapper">
			<h1>문의글 작성</h1>
			<table class="cus_show">
				<tbody>
					<tr>
						<th><label for="suggest_type">문의유형</label></th>
												
						<td>	
							<select name="cate_code" id="suggest_type">
							<c:forEach var="c" items="${cate}">
							<option value="${c.cate_code}">${c.cate_name}</option>								
							</c:forEach>
							</select>
						</td>		
					</tr>
					
					<tr>
						<th><label for="board_password">비밀번호</label></th>
						<td>
							<input type="password" name="board_password" id="board_password">
						</td>
					</tr>
					
					<tr>
						<th><label for="title">제목</label></th>
						<td><input type="text" name="board_title" id="title" required></td>
					</tr>

					<tr>
						<th><label for="content">내용</label></th>
						<td><textarea name="board_content" id="content" rows="8" cols="60" required></textarea></td>
					</tr>

					<tr>
						<th><label for="upload_file">첨부파일</label></th>
						<td><input type="file" name="uploadFile" multiple>
							<div class="resultDiv"></div>
						</td>
					</tr>
				</tbody>					
			</table>
			<!-- cus_show-->
			<!-- 확인 취소버튼 -->
			<div class="write_btn">
				<button type="submit" id="save">등록</button>
				<button type="button" id="cancel">취소</button>
			</div>
		</div> <!-- table_wapper-->
		</form> 
</body>
</html>