function updNotice(notice_no) {
	$(location).attr('href', "updNotice?notice_no=" + notice_no);
}

/*function delNotice(notice_no) {
	var fileName = $("#fileName").val();
	$(location).attr('href', "deleteFile?fileName=" + fileName);	
	$(location).attr('href', "delNotice?notice_no=" + notice_no);
}*/

$(document).ready(function(){
	var uuid = $("#uuid").val();
	var fileType = $("#fileType").val();
	var uploadPath = $("#uploadPath").val();
	var fileName = $("#fileName").val();
	var str ="";
	
	$(".downFile").on("click", function(e){
		console.log("view image");
		
//		var path = encodeURIComponent(liObj.data("path")+"/" + liObj.data("uuid") +"_"+ liObj.data("filename"));
		var path2 = encodeURIComponent(uploadPath+"/"+uuid+"_"+fileName);
		
			self.location = "/download?fileName="+path2;					
	});
	$("#btnDelete").click(function(){		
		var notice_no = $("#notice_no").val();
		var fileName = $("#fileName").val();
		var uploadPath = $("#uploadPath").val();
		var uuid = $("#uuid").val();
		var path = encodeURIComponent(uploadPath+"/"+uuid+"_"+fileName);
		$.ajax({
			url: '/deleteFile',
			data: {fileName : path},			
			dataType : 'text',
			type: 'POST',
				success: function(result){
					$(location).attr('href', "delNotice?notice_no=" + notice_no);	
			}
		});
	});
});
