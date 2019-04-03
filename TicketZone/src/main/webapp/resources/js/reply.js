$(function() {
// 답글 등록시
	$('#reply_submit').click(function() {
		var query = {
			board_no : $('#board_no').val(),
			reply_content : $('#reply_content').val()
		}
		
		if( query.reply_content == "") {
			alert('답글을 입력하세요');
			$('#reply_content').focus();
			return false;
		}
				
		$.ajax({
			type: 'post',
			url: 'addReply',
			data: query,
			success: function(data) {
				$('#reply_head span').val("");
				$('#reply_head span').append(data[0].reply_reg);
			}
		})
	})
	
})

function reply_update(board_no) {
// $(location).attr('href', 'reply_update/board_no='+board_no);
}

function reply_delete(board_no) {
	$(location).attr('href', 'reply_delete?board_no='+board_no);
}