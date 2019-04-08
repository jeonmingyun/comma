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
});