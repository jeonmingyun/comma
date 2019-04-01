$(function() {
	$('#update').click(function() {
		var title = $('#title').val();
		var content = $('#content').val();

		if(title == "") {
			alert("제목을 입력하세요");
			$('#title').focus();
			return false;
		} else if(content == "") {
			alert("내용을 입력하세요");
			$('#content').focus();
			return false;
		}
	})
})