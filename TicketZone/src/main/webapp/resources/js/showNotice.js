function updNotice(notice_no) {
	$(location).attr('href', "updNotice?notice_no=" + notice_no);
}

function delNotice(notice_no) {
	$(location).attr('href', "delNotice?notice_no=" + notice_no);
}