<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script src="../resources/js/reply.js"></script>

<!-- 답글 쓰기 -->
<div class="addReply">
	<form action="addReply" method="post">
		<div>
			<input type="hidden" name="board_no" value="${InquiryUpd[0].board_no}">
			<div>
				<strong>답글</strong>
			</div>
			<div>
				<textarea id="reply_content" name="reply_content" placeholder="답글 추가"></textarea>
			</div>
			<div>
				<button type="submit" id="reply_submit">등록</button>
			</div>
		</div>
	</form>
</div>

<!-- 답글 리스트 -->
<div class="replyList">
	<form name="replyListForm" method="post">
		<div id="reply"></div>

		<div>
			<button id="reply_update">수정</button>
			<button id="reply_delete">삭제</button>
		</div>
	</form>
</div>