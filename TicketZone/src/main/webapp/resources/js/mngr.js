$(document).ready(function(){
	$("#join_btn").click(function(){
		location.href = "/mngr_register";
	});
		$("#login_btn").click(function(){
			alert("클릭");
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
					if(data == "success"){
						alert(data);
						window.location.href = "/success";
					}else{
						document.getElementById("loginfail").innerHTML = "아이디 또는 비밀번호가 틀렸습니다.";
					}
				}
			});
		});
	
});