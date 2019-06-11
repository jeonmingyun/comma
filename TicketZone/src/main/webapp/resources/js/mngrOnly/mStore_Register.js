var mapContainer = document.getElementById('map'), // 지도를 표시할 div
mapOption = {
	center : new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
	level : 5
// 지도의 확대 레벨
};

//지도를 미리 생성
var map = new daum.maps.Map(mapContainer, mapOption);
//주소-좌표 변환 객체를 생성
var geocoder = new daum.maps.services.Geocoder();
//마커를 미리 생성
var marker = new daum.maps.Marker({
	position : new daum.maps.LatLng(37.537187, 127.005476),
	map : map
});

function sample5_execDaumPostcode() {
	new daum.Postcode({
		oncomplete : function(data) {
			var addr = data.address; // 최종 주소 변수
			console.log(data);
			// 주소 정보를 해당 필드에 넣는다.
			document.getElementById("sample5_address").value = addr;
			// 주소로 상세 정보를 검색
			geocoder.addressSearch(data.address, function(results, status) {
				// 정상적으로 검색이 완료됐으면

				if (status === daum.maps.services.Status.OK) {

					var result = results[0]; //첫번째 결과의 값을 활용
					document.getElementById("coor_x").value = result.x;
					document.getElementById("coor_y").value = result.y;
					
					// 해당 주소에 대한 좌표를 받아서
					var coords = new daum.maps.LatLng(result.y, result.x);
					// 지도를 보여준다.
					mapContainer.style.display = "block";
					map.relayout();
					// 지도 중심을 변경한다.
					map.setCenter(coords);
					// 마커를 결과값으로 받은 위치로 옮긴다.
					marker.setPosition(coords)
				}
			});
		}
	}).open();
}
	
	function handleImgFileSelect(e){
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		
		filesArr.forEach(function(f){
			if(!f.type.match("image.*")){
				alert("확장자는 이미지 확장자만 가능합니다.");
				return
			}
			sel_file = f;
			
			var reader = new FileReader();
			reader.onload = function(e){
				$("#preview").attr("src", e.target.result);
				$("#preview").show();
			}
			reader.readAsDataURL(f);
		});
	}

	var maxSize = 5242880; //5MB

	function checkExtension(fileSize) {
	if (fileSize >= maxSize) {
		alert("파일 사이즈 초과");
		return false;
	} else {
		return true;
	}
	
	}
	$(document).ready(function(){			
		
		
		var formObj = $("#insert_Store");

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
						uploadSuccess(result);
					}
			}); //$.ajax
		});
		var resultDiv = $(".resultDiv");
		function uploadSuccess(uploadResultArr){
			var str = "";
			var time1 = $("#time").val();
			var time2 = $("#time2").val();
			var store_time = time1 + "-" + time2;
			$(uploadResultArr).each(function(i,obj){
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/" + obj.uuid +"_"+obj.fileName);
				
				str += "<span data-file=\'"+fileCallPath+"\' data-type='file'>삭제하기</span>";
				str += "<input type='hidden' name='img_filename' value='"+obj.fileName+"'>";
				str += "<input type='hidden' name='img_uuid' value='"+obj.uuid+"'>";
				str += "<input type='hidden' name='img_uploadpath' value='"+obj.uploadPath+"'>";
				str += "<input type='hidden' name='store_time' value='"+store_time+"'>"; 				
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
	$("#upload_img").on("change", handleImgFileSelect);
});