function updInquiry(board_no) {
	$(location).attr('href', "updInquiry?board_no=" + board_no);
}


function delInquiry(board_no) {
	$(location).attr('href', "delInquiry?board_no=" + board_no);
	
}