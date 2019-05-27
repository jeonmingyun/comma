$(function() {
	$("#cancel").click(function() {
		// 뒤로가기
		history.go(-1);
		return false;
	});
	
//	수정하기 엔터키
	var a = $("#content").val();
	var a = a.split('<br />').join("\r\n");
	/*a = a.split('<br>').join("\r\n");*/
	console.log(a);
	$("#content").empty();
	$("#content").append(a);
	
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