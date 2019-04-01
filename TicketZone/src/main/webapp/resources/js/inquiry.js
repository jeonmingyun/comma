$(document).ready(function() {
	$("#btnwrite").click(function() {
		// 페이지 주소 변경(이동)
		$(location).attr("href", "inquiryWrite");
	});
});

function showInquiry(board_no) {
	$(location).attr('href', 'showInquiry?board_no='+board_no);
}