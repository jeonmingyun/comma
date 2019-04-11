$(document).ready(function() {
	$(".license").click(function() {
		var query = $(this).val();
		alert(query);

		$.ajax({
			type : "post",
			dataType : "text",
			data : query,
			url : "store.do",
			success : function(data) {
				$(location).attr("href", "store/store_info");
			}
		});
	});
	
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