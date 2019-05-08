$(document).ready(function(){
	// 엔터키 누르면 비밀번호 입력창으로
	$("#mngr_id").keydown(function(key){
		if(key.keyCode == 13) {
			$("#mngr_pw").focus(); // 암호에 포커스
		}
	})
	// 엔터키 누르면 로그인되게
	$("#mngr_pw").keydown(function(key) {
	    if (key.keyCode == 13) {
	    	$("#login_btn").click();
	    }
	});
	
	$("#join_btn").click(function(){
		location.href = "/mngr_register";
	});
	
	$("#login_btn").click(function(){
		var id = $("#mngr_id").val();
		var password = $("#mngr_pw").val();
		
		if(id == ""){
			alert("아이디를 입력하세요.");
			$("#mngr_id").focus();
			return;
		}
		
		if(password == ""){
			alert("비밀번호를 입력하세요.");
			$("#mngr_pw").focus();
			return;
		}
		
		var query = {id, password};
		$.ajax({
			type : "post",
			dataType : "text",
			data : query,
			url : "/login.do",
			success : function(data){
				if(data == "mngrOnly"){
					window.location.href = "/mngrOnly";
				}else{
					document.getElementById("loginfail").innerHTML = "아이디 또는 비밀번호가 틀렸습니다.";
				}
			}
		});
	});
	
});