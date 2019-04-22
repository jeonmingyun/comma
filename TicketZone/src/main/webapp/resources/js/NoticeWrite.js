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
	
	$(document).ready(function(){
		
		
		var formObj = $("#insert_from");

		$("input[type='submit']").on("click", function(e){
			e.preventDefault();
			console.log("submit clicked");
			var str = "";
			formObj.submit();
			
		});
		
		$("input[type='file']").change(function(e){
			
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			for(var i=0; i<files.length; i++){
				if(!checkExtension(files[i].name, files[i].size)){
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
						uploadSuccess(result);
					}
			}); //$.ajax
		});
		var resultDiv = $(".resultDiv");
		function uploadSuccess(uploadResultArr){
			var str = "";
			
			$(uploadResultArr).each(function(i,obj){
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/" + obj.uuid +"_"+obj.fileName);
				str += "<span data-file=\'"+fileCallPath+"\' data-type='file'>"+obj.fileName+"삭제하기</span>";
				str += "<input type='hidden' name='fileName' value='"+obj.fileName+"'>";
				str += "<input type='hidden' name='uuid' value='"+obj.uuid+"'>";
				str += "<input type='hidden' name='uploadPath' value='"+obj.uploadPath+"'>";				
				str += "<input type='hidden' name='notice_status' value='1'>";
			});
			resultDiv.append(str);
		}
		
		
		$(".resultDiv").on("click", "span", function(e){
			console.log("delete file");
			
			var targetFile = $(this).data("file");
			var type = $(this).data("type");
			
			var targetLi = $(this).closest("span");
			
			$.ajax({
				url: '/deleteFile',
				data: {fileName: targetFile, type:type},
				dataType: 'text',
				type: 'POST',
					success: function(result){
						alert(result);
						targetLi.remove();
						
					}
			});
		});
		$("#write_cancle").on("click", function(){
			console.log("클릭");
			history.go(-1);	
		});
		
	});
	