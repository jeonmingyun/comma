$(document).ready(function() {
	$("#btnwrite").click(function() {
		// 페이지 주소 변경(이동)
		$(location).attr("href", "inquiryWrite");
	});
	
		var actionForm = $("#actionForm");
		$(".paginate_button a").on("click", function(e){			
			e.preventDefault();
			
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});	
});

function showInquiry(board_no) {
	$(location).attr('href', 'showInquiry?board_no='+board_no);
}