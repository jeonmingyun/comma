function updInquiry(board_no) {
	$(location).attr('href', "mUpdInquiry?board_no=" + board_no);
}


function delInquiry(board_no) {
	$(location).attr('href', "mDelInquiry?board_no=" + board_no);
	
}