$(document).ready(function() {
	$("#cancel").click(function() {
		// 뒤로가기
		history.go(-1);
		return false;
	});
});