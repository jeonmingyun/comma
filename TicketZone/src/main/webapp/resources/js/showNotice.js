function updNotice(notice_no) {
	$(location).attr('href', "updNotice?notice_no=" + notice_no);
}

function delNotice(notice_no) {
	$(location).attr('href', "delNotice?notice_no=" + notice_no);
}

$(document).ready(function(){
	var uuid = $("#uuid").val();
	var fileType = $("#fileType").val();
	var uploadPath = $("#uploadPath").val();
	var fileName = $("#fileName").val();
	var str ="";
	
	
	
	if(fileType){
	var fileCallPath = encodeURIComponent(uploadPath +"/s_" + uuid + "_" + fileName);
		str += "<li data-path='"+uploadPath+"' data-uuid='"+uuid+"' data-filename='"+fileName+"' data-type='"+fileType+"'><div>";
		str += "<img src='/display?fileName="+fileCallPath+"'>";
		str += "</div>";
		str + "</li>";
		console.log(str);
	} else {
		
		str += "<li data-path='"+uploadPath+"' data-uuid='"+uuid+"' data-filename='"+fileName+"' data-type='"+fileType+"'><div>";
		str += "<span> " + fileName + "</span><br/>";
		str += "</div>";
		str +"</li>";
		console.log(str);
	}
	$(".uploadResult ul").html(str);
	
	$(".uploadResult").on("click", "li", function(e){
		console.log("view image");
		var liObj = $(this);
		console.log(this);
		var path = encodeURIComponent(liObj.data("path")+"/" + liObj.data("uuid") +"_"+ liObj.data("filename"));
		
		if(liObj.data("type")){
			self.location = "/download?fileName="+path
			
		} else {
			console.log("error");
		}
	});
});
