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
	$('#cancel').click(function() {
		$(location).attr('href', "notice");
	});
	
	$('#update').click(function() {
		var title = $('#title').val();
		var content = $('#content').val();
		var notice_no = $("input[name='notice_no']").val();
		var notice_title = $("input[name='notice_title']").val();
		var notice_content = $("textarea[name='notice_content']").val();
		var notice_status = $("input[name='notice_status']").val();
		var uuid = $("input[name='uuid']").val();
		var uploadPath = $("input[name='uploadPath']").val();
		var fileName = $("input[name='fileName']").val();
		var query = [];
		console.log(uuid);
		if( title == "") {
			alert("제목을 입력하세요");
			$('#title').focus();
			return false;
		} else if( content == "") {
			alert("내용을 입력하세요");
			$('#content').focus();
			return false;
		}
		if(uuid == null){			
			query = {
					notice_no : notice_no,
					notice_title : notice_title,
					notice_content : notice_content,
					notice_status : '0'
			};
		}else {
			query = {
				notice_no : notice_no,
				notice_title : notice_title,
				notice_content : notice_content,
				notice_status : '1',
				uuid : uuid,
				uploadPath : uploadPath,
				fileName : fileName
			};
		}
		console.log(query);
		$.ajax({
			url: '/update',
			data : query,
			dataType : 'text',
			type: 'POST',
			success: function(data){
				$(location).attr('href', "notice");
			}
		});
	});
	$(document).on("click","#deleteFile",function(){
		console.log("click");
		var notice_no = $("input[name='notice_no'").val();
		console.log(notice_no);
		var fileName = $("#fileName").val();
		var uploadPath = $("#uploadPath").val();
		var uuid = $("#uuid").val();
		var path = encodeURIComponent(uploadPath+"/"+uuid+"_"+fileName);
		$.ajax({
			url: '/deleteFile',
			data: {fileName : path,
				   notice_no : notice_no},			
			dataType : 'text',
			type: 'POST',
				success: function(result){
				 	$(location).attr('href', "updNotice?notice_no=" + notice_no);
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
			str += "<input type='hidden' name='fileName' value='"+obj.fileName+"'>";
			str += "<input type='hidden' name='uuid' value='"+obj.uuid+"'>";
			str += "<input type='hidden' name='uploadPath' value='"+obj.uploadPath+"'>";				
			str += "<input type='hidden' name='notice_status' value='1'>";
		});
		modifyDiv.append(str);
	}
});



	

