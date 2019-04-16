<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
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
		function showUploadResult(uploadResultArr) {
			if(!uploadResultArr || uploadResultArr.length == 0){return;}
			var uploadUL = $(".uploadResult ul");
			var str = "";

			$(uploadResultArr).each(
					function(i, obj) {
						
						if (!obj.image) {
							console.log(obj.uploadPath);
							//다운로드	
								var fileCallPath = encodeURIComponent( obj.uploadPath+ "/s_" + obj.uuid +"_"+obj.fileName);							
								str += "<li data-path='"+obj.uploadPath+"'";
								str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'"  + str +"' ><div>";
								str += "<span> " + obj.fileName+"</span>";
								str += "<button type='button' data-file=\'"+fileCallPath+"\' "
								str += "data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
								str += "<img src='/display?fileName="+fileCallPath+"'>";
								str += "</div>";
								str += "</li>";
							} else {
								
								var fileCallPath = encodeURIComponent( obj.uploadPath+"/"+ obj.
										uuid +"_"+obj.fileName);
								var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
								
								str += "<li "
								str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"' ><div>";
								str += "<span> " + obj.fileName+"</span>";
								str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' class='btn btn-warning btn-circle'>";
								str += "<i class='fa fa-times'></i></button><br>";
								str += "<img src='/resources/img/attach.png'></a>";
								str += "</div>";
								str += "</li>";
							}

					});
			uploadUL.append(str);
		}
		
		var formObj = $("#insert_from");

		$("input[type='submit']").on("click", function(e){
			e.preventDefault();
			console.log("submit clicked");
			var str = "";
			$(".uploadResult ul li").each(function(i, obj){				
				var jobj = $(obj);
				console.dir(jobj);
				str += "<input type='hidden' name='fileName' value='"+jobj.data("filename")+"'>";
				str += "<input type='hidden' name='uuid' value='"+jobj.data("uuid")+"'>";
				str += "<input type='hidden' name='uploadPath' value='"+jobj.data("path")+"'>";
				str += "<input type='hidden' name='fileType' value='"+jobj.data("type")+"'>";
				console.log(str);
			});
			formObj.append(str).submit();
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
						showUploadResult(result);
						
						
					}
			}); //$.ajax
		});
		$(".uploadResult").on("click", "button", function(e){
			console.log("delete file");
			
			var targetFile = $(this).data("file");
			var type = $(this).data("type");
			
			var targetLi = $(this).closest("li");
			
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
		
	});
	
</script>
<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<form method="post" id="insert_from" action="/noticeInsert">
		제목:<input type="text" id="notice_title" name="notice_title"><br>
		내용:
		<textarea id="notice_content" name="notice_content"></textarea>
		<!-- 파일업로드 -->
		<div class="uploadDiv">
			<input type="file" name="uploadFile" multiple>
		</div>
		<div class="uploadResult">
			<ul>

			</ul>
		</div>
		<br> <input type="submit" value="글작성">
	</form>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>