var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880; //5MB

	function checkExtension(fileName, fileSize) {
		if (fileSize >= maxSize) {
			alert("파일 사이즈 초과");
			return false;
		}

		if (regex.test(fileName)) {
		alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}	
			return true;
	}

$(function() {
	$("#cancel").click(function() {
		// 뒤로가기
		history.go(-1);
		return false;
	});
	
	$('#update').click(function() {
		var title = $('#title').val();
		var content = $('#content').val();
		var board_no = $("input[name='board_no']").val();
		var board_title = $("input[name='board_title']").val();
		var board_content = $("textarea[name='board_content']").val();		
		var inq_uuid = $("input[name='inq_uuid']").val();
		var inq_uploadpath = $("input[name='inq_uploadpath']").val();
		var inq_filename = $("input[name='inq_filename']").val();
		var query = [];
		
		if( title == "") {
			alert("제목을 입력하세요");
			$('#title').focus();
			return false;
		} else if( content == "") {
			alert("내용을 입력하세요");
			$('#content').focus();
			return false;
		}
		if(inq_uuid == null){			
			query = {
					board_no : board_no,
					board_title : board_title,
					board_content : board_content,
					
			};
		}else {
			query = {
				board_no : board_no,
				board_title : board_title,
				board_content : board_content,				
				inq_uuid : inq_uuid,
				inq_uploadpath : inq_uploadpath,
				inq_filename : inq_filename
			};
		}
		console.log(query);
		$.ajax({
			url: '/updInquiryForm',
			data : query,
			dataType : 'text',
			type: 'POST',
			success: function(data){
				$(location).attr('href', "inquiry");
			}
		});
	});
	$(document).on("click","#deleteFile",function(){
		console.log("click");
		var board_no = $("input[name='board_no'").val();
		console.log(notice_no);
		var inq_filename = $("#inq_filename").val();
		var inq_uploadpath = $("#inq_uploadpath").val();
		var inq_uuid = $("#inq_uuid").val();
		var path = encodeURIComponent(inq_uploadpath+"/"+inq_uuid+"_"+inq_filename);
		$.ajax({
			url: '/deleteFile',
			data: {fileName : path,
				   board_no : board_no},			
			dataType : 'text',
			type: 'POST',
				success: function(result){
				 	$(location).attr('href', "updInquiry?board_no=" + board_no);
			}
		});
	});
	
	$("input[type='file']").change(function(e){
		
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		for(var i=0; i<files.length; i++){
			if(!checkExtension(files[i].size)){
				return false;
			}
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url: '/uploadAjaxAction',
			processData: false,
			contentType: false,data:
			formData,type: 'POST',
			dataType: 'json',
				success: function(result){
					console.log(result);
					$('br').remove();
					$("#uploadFile").append("<a href='#'>"+result[0].fileName+"</a><button id='deleteFile'>삭제하기</button>");
					uploadSuccess(result);
				}
		}); //$.ajax
	});
	var modifyDiv = $(".modifyDiv");
	function uploadSuccess(uploadResultArr){
		var str = "";
		$(uploadResultArr).each(function(i,obj){		
			str += "<input type='hidden' name='inq_filename' value='"+obj.fileName+"'>";
			str += "<input type='hidden' name='inq_uuid' value='"+obj.uuid+"'>";
			str += "<input type='hidden' name='inq_uploadpath' value='"+obj.uploadPath+"'>";							
		});
		modifyDiv.append(str);
	}
});