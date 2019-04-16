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
		<div class="resultDiv">
			
		</div>
		
		<br> <input type="submit" value="글작성"><br>		
	</form>
		<button id="write_cancle">취소</button>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>