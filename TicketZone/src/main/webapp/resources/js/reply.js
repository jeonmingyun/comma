$(function() {
	// 답글 등록시
	$('.addReply_submit').click(function() {
		var query = {
			board_no : $('#board_no').val(),
			reply_content : $('#addReply_content').val().trim()
		}
		
		if( query.reply_content == "") {
			alert('답글을 입력하세요');
			$('#addReply_content').focus();
			return false;
		}
				
		$.ajax({
			type: 'post',
			url: 'addReply',
			data: query,
			success: function(data) {
				$('#addReply_content').val("");
				$('#reply_head span').text("");
				$('#reply_content').text("");
				$('#reply_head span').append(data[0].reply_reg);
				$('#reply_content').append(data[0].reply_content);
				$('.addReply_submit').toggleClass('addReply_submit updReply_submit');
			}
		})
	})
	
	$('.updReply_submit').click(function() {
		var query = {
			reply_content : $('#reply_content').text().trim(),
			board_no : $('#board_no').val()
		}
		console.log(query);
		$.ajax({
			type: 'post',
			url: 'reply_update',
			data: query,
			success: function(data) {
				console.log('success');
			},
			error: function(data) {
				console.log('error');
			}
		})
	})
	
})

function reply_update_info(board_no) {
	$('#addReply_content').val($('#reply_content').text().trim());
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
		}
	})
}