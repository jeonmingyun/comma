<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script src="../resources/js/reply.js"></script>
<link href="../resources/css/reply.css" rel="stylesheet">
<!-- 답글 쓰기 -->
<div class="addReply">
 	<div>
		<input type="hidden" id="board_no" name="board_no" value="${InquiryUpd[0].board_no}">
		<div>
			<strong>답글</strong>
		</div>
		<div>
			<textarea id="addReply_content" name="reply_content" placeholder="답글 추가"></textarea>
		</div>
		<div>
			<c:if test="${empty replyList}">
				<button type="submit" class="addReply_submit">등록</button>
			</c:if>
			<c:if test="${!empty replyList}">
				<button type="submit" class="updReply_submit">등록</button>
			</c:if>
		</div>
	</div>
</div>

<!-- 답글 리스트 -->
<div class="replyList">
	<div id="reply">
		<div id="reply_head">
			<strong>관리자</strong>
			<span>${replyList[0].reply_reg}</span>
		</div>
		<div id="reply_content">
			${replyList[0].reply_content}			
		</div>
		<div id="reply_button">
			<button onclick="reply_update_info(${param.board_no })">수정</button>
			<button onclick="reply_delete(${param.board_no })">삭제</button>
		</div>
	</div>

</div>