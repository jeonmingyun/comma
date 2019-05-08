$(document).ready(function(){
	var actionForm = $("#actionForm");
	$(".paginate_button a").on("click", function(e){
		e.preventDefault();
		
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	});	
	
//	var searchForm = $("#searchForm");
//	$("#searchForm button").on("click", function(e){
//		e.preventDefault();
//		searchForm.find("input[name='pageNum']").val("1");
//		searchForm.submit();
//	});
});

function showNotice(notice_no) {
	$(location).attr('href', "showNotice?notice_no=" + notice_no);
}

function noticeWrite(){
	window.location.href = "/noticeWrite"
}

