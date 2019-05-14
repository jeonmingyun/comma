<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script src="../resources/js/reply.js"></script>
<link href="../resources/css/reply.css" rel="stylesheet">
<!-- 답글 쓰기 -->
<div id="addReply">
 	<div>
		<input type="hidden" id="board_no" name="board_no" value="${InquiryUpd[0].board_no}">
		<!-- <div>
			<strong>답글</strong>
		</div> -->
		<!-- <div> -->
			
		<!-- </div> -->
		<div class="reply_btn">
		<textarea id="addReply_content" name="reply_content" placeholder="답글 추가" rows="1"></textarea>
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
<div id="replyList">
		<div id="reply_head">
			<strong>관리자</strong>
			<span><fmt:formatDate value="${replyList[0].reply_reg}" pattern="yyyy.MM.dd HH:mm:ss"/></span>
			
			<div id="reply_icon">
				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M0 0h24v24H0z" fill="none"/><path d="M12 8c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zm0 2c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm0 6c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2z"/></svg>
			</div>
			
		</div>
	
		<div id="reply_content">
			${replyList[0].reply_content}			
		</div>
		<div id="reply_button">
			<ul>
				<li onclick="reply_update_info(${param.board_no })"><button>수정</button><svg xmlns="http://www.w3.org/2000/svg" width="13" height="13" viewBox="0 0 24 24"><path d="M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z"/><path d="M0 0h24v24H0z" fill="none"/></svg></li>
				<li onclick="reply_delete(${param.board_no })"><button>삭제</button><svg xmlns="http://www.w3.org/2000/svg" width="13" height="13" viewBox="0 0 24 24"><path d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z"/><path d="M0 0h24v24H0z" fill="none"/></svg></li>
			</ul>
		</div>
</div>

<!-- <script>
	$(document).ready(function(){
		$("$reply_icon").click(function(){
			if($("$reply_button").is(":visible")){
				$("$reply_button").css("display", "block");
			}else{
				$("$reply_button").css("display", "none");
			}
		})
	});
</script> -->