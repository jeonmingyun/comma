function updInquiry(board_no) {
	$(location).attr('href', "updInquiry?board_no=" + board_no);
}


function delInquiry(board_no) {
	$(location).attr('href', "delInquiry?board_no=" + board_no);
	
}

$(document).ready(function(){
	var uuid = $("#inq_uuid").val();	
	var uploadPath = $("#inq_uploadpath").val();
	var fileName = $("#inq_filename").val();
	var str ="";
	
	$(".downFile").on("click", function(e){
		console.log("view image");
		
//		var path = encodeURIComponent(liObj.data("path")+"/" + liObj.data("uuid") +"_"+ liObj.data("filename"));
		var path2 = encodeURIComponent(uploadPath+"/"+uuid+"_"+fileName);
		
			self.location = "/download?fileName="+path2;					
	});
});