$(document).ready(function() {
	
	$(".Ticketbtn").click(function(){
		var query = {
			member_tel : $(this).val(),
			license_number : $(this).attr('name')
		}
		
		$.ajax({
			type: 'post',
			url: 'issue_ticket',
			data: query,
			success: function(data) {
				console.log('success');
				$(location).attr('href', '/mCustomer?license_number='+query.license_number);
			}
		})
	});
	
	$(".Paybtn").click(function(){
		var query = {
				license_number : $("#payment").val()
		}
		$.ajax({
			type: 'post',
			url: 'minus_ticket',
			data: query,
			success: function(data){
				console.log('success');
				$(location).attr('href', '/mCustomer?license_number='+query.license_number);
			}
		})
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

