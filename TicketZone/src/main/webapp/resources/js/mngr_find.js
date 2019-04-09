$(function(){
            // $('input[type=radio][name=id_find]').on('click',function(){
            $(".id_find").click(function(){
                var checkedValue = $(".id_find:checked").val();
                
                if(checkedValue == 'phone_num'){
                    $("#phone_ok").css('display','block');
                    $("#email_ok").css('display','none');
                } else if (checkedValue == 'e_mail'){
                    $("#phone_ok").css('display','none');
                    $("#email_ok").css('display','block');
                }
            });
            
            $(".find_btn").click(function(){
            	var owner_name = $("#owner_name").val();
            	var owner_tel = $("#owner_tel").val();
            	
            	if(owner_name == ""){
            		alert("이름을 입력하세요.");
            		$("#owner_name").focus;
            		return;
            	}
            	
            	if(owner_tel == ""){
            		alert("비밀번호를 입력하세요.");
            		$("#owner_tel").focus;
            		return;
            	}
            	
            	var query = {owner_name, owner_tel}
            	$.ajax({
            		type : "post",
            		dataType : "text",
            		data : query,
            		url : "/mngr_findTel_pro",
            		success : function(data){
            			if(data == "mngr_findTel"){
            				window.location.href = "/mngr_findTel";
            			}else{
            				document.getElementById("findfail").innerHTML = "이름 또는 전화번호가 틀렸습니다.";
            			}
            		}
            	})
            });
            
        });