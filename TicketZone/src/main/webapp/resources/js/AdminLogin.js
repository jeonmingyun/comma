$(document).ready(function(){
$("#Login_btn").click(function(){
		var admin_id = $("#admin_id").val();
		var admin_pass = $("#admin_pass").val();
		
		if(admin_id == ""){
			alert("아이디를 입력하세요.");
			$("#admin_id").focus();
			return;
		}
		
		if(admin_pass == ""){
			alert("비밀번호를 입력하세요.");
			$("#admin_pass").focus();
			return;
		}
		
		var query = {admin_id, admin_pass};
		$.ajax({
			type : "post",
			dataType : "text",
			data : query,
			url : "/adminLogin.do",
			success : function(data){
				if(data == "success"){
					window.location.href = "/";
				}else{
					document.getElementById("loginfail").innerHTML = "아이디 또는 비밀번호가 틀렸습니다.";
				}
			}
		});
	});
});