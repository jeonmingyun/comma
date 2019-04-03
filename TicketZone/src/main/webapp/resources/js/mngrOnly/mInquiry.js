$(document).ready(function() {
	$("#btnwrite").click(function() {
		// 페이지 주소 변경(이동)
		$(location).attr("href", "mInquiryWrite");
	});
});

function mShowInquiry(board_no) {
	$(location).attr('href', 'mShowInquiry?board_no='+board_no);
}