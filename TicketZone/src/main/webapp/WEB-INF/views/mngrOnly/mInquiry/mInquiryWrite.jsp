<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>고객센터 게시글 작성</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- <link href="../resources/css/inquiryWrite.css" rel="stylesheet" />-->
<script src="../resources/js/mngrOnly/mInquiryWrite.js"></script>

</head>
<body>
	<!-- 고객센터 문의글 작성 -->
	<form name="suggest" method="post" action="/mInsertInquiry">
		<div class="table_wapper">
			<h1>문의글 작성</h1>
			<table class="cus_show">
				<tbody>
					<tr>
						<th><label for="suggest_type">문의 유형</label></th>
						<td><select name="suggest_type" id="suggest_type">
								<option value="error">앱 오류</option>
								<option value="else">기타</option>
						</select></td>
					</tr>

					<tr>
						<th><label for="title">제목</label></th>
						<td><input type="text" name="board_title" id="title" size="60"></td>
					</tr>

					<tr>
						<th><label for="content">내용</label></th>
						<td><textarea name="board_content" id="content" rows="8" cols="60"></textarea></td>
					</tr>

					<!-- <tr>
						<th><label for="attached_file">첨부파일</label></th>
						<td><input type="file" name="attached_file"
							id="attached_file"></td>
					</tr> -->
			</table>
			<!-- cus_show-->
			<!-- 확인 취소버튼 -->
			<div class="write_btn">
				<button type="submit" id="save">등록</button>
				<button type="button" id="cancel">취소</button>
			</div>
		</div>
		<!-- table_wapper-->
	</form>
</body>
</html>