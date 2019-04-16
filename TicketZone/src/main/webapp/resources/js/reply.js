$(function() {
	var reply_submit_class = $('#addReply button').attr('class').trim();

	if( reply_submit_class == "addReply_submit" ) {
		$('#replyList').hide(); // 답글 없을 때
	}else {
		$('#addReply').hide(); // 답글 있을 때
	}
	
	// 답글 등록
	$(document).on('click', '.addReply_submit', function() {
		var query = {
			board_no : $('#board_no').val(),
			reply_content : $('#addReply_content').val().trim()
		}
		
		/*답글 내용 미입력시*/
		if( query.reply_content == "") {
			alert('답글을 입력하세요');
			$('#addReply_content').focus();
			return false;
		}
				
		$.ajax({
			type: 'post',
			url: 'addReply',
			data: query,
			dataType: 'json',
			success: function(data) {
				$('#addReply_content').val("");
				$('#reply_head span').text(data[0].reply_reg);
				$('#reply_content').text(data[0].reply_content);
				$('.addReply_submit').toggleClass('addReply_submit updReply_submit');
				$('#replyList').show();
				$('#addReply').hide();
			}
		})
	})
	
	//답글 수정 처리
	$(document).on('click', '.updReply_submit', function() {
		var query = {
			reply_content : $('#addReply_content').val().trim(),
			board_no : $('#board_no').val()
		}
		
		$.ajax({
			type: 'post',
			url: 'reply_update',
			data: query,
			success: function(data) {
				$('#addReply_content').val("");
				$('#reply_content').text(query.reply_content);
				$('#replyList').show();
				$('#addReply').hide();
			}
		})
	})
	
})

//답글 수정 요청
function reply_update_info(board_no) {
	$('#addReply_content').val($('#reply_content').text().trim());
	$('#replyList').hide();
	$('#addReply').show();
}

function reply_delete(board_no) {
	$.ajax({
		type: 'post',
		url: 'reply_delete',
		data: {board_no: board_no},
		success: function(data) {
			$('#reply_head span').text("");
			$('#reply_content').text("");
			$('.updReply_submit').toggleClass('addReply_submit updReply_submit');
			$('#replyList').hide();
			$('#addReply').show();
		}
	})
}