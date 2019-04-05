$(document).ready(function() {
	var issueForm = $("#issueForm");
	var successForm = $("#successForm");
	$("#issueForm button").on("click", function(e) {
		e.preventDefault();
		issueForm.submit();
		
	});
	$("#successForm button").on("click", function(e) {
		e.preventDefault();
		successForm.submit();
	});
	
	/*매장 option selected 지정*/
	var select_license_number = $('#select_license_number').val();
	$('option[value='+select_license_number+']').attr('selected', 'selected');

	$("#storelist").change(function(){
		var license_number = $("#storelist").val();
		/*고객 대기 목록*/
		$.ajax({
			type : 'post',
			url : 'getLicense',
			data : {license_number : license_number},
			success: function(data) {
				$(location).attr('href', '/mCustomer?license_number='+license_number);
			}
		});
		
	});
	
});

