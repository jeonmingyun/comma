$(document).ready(function() {
	 $.ajax({
	        type: "get",
	        url: "http://openapi.nsdi.go.kr/nsdi/eios/service/rest/AdmService/admCodeList.json",
	        data : {authkey : $('#sido_key').val()},
	        async: false,
	        dataType: 'json',
	        success: function(data) {
		        var html = "<option>선택</option>";
		
		        for(var i=0;i<data.admVOList.admVOList.length;i++){ 
			        html +="<option value='"+data.admVOList.admVOList[i].admCode+"'>"+data.admVOList.admVOList[i].lowestAdmCodeNm+"</option>"
          }
		
          $('#sido_code').html(html);
		
	        },
	        error: function(xhr, stat, err) {}
  });

  $(document).on("change","#sido_code",function(){
	        var thisVal = $(this).val();
	
         $.ajax({
		        type: "get",
	    	    url: "http://openapi.nsdi.go.kr/nsdi/eios/service/rest/AdmService/admSiList.json",
		        data : {admCode : thisVal, authkey : $('#sigoon_key').val()},
		        async: false,
		        dataType: 'json',
		        success: function(data) {
			        var html = "<option>선택</option>";
			
			        for(var i=0;i<data.admVOList.admVOList.length;i++){ 
			        	html +="<option value='"+data.admVOList.admVOList[i].admCode+"'>"+data.admVOList.admVOList[i].lowestAdmCodeNm+"</option>"
              }
                 $('#sigoon_code').html(html);	
		        },
		        error: function(xhr, stat, err) {}
	        });
     });

     $(document).on("change","#sigoon_code",function(){ 
	        var thisVal = $(this).val();		
	
	        $.ajax({
		        type: "get",
		        url: "http://openapi.nsdi.go.kr/nsdi/eios/service/rest/AdmService/admDongList.json",
		        data : {admCode : thisVal, authkey : $('#dong_key').val()},
		        async: false,
		        dataType: 'json',
		        success: function(data) {
			        var html = "<option>선택</option>";
			
			        for(var i=0;i<data.admVOList.admVOList.length;i++){ 
				        html +="<option value='"+data.admVOList.admVOList[i].admCode+"'>"+data.admVOList.admVOList[i].lowestAdmCodeNm+"</option>"
			        }
                 $('#dong_code').html(html);
		        },
		        error: function(xhr, stat, err) {}
	        });
     });
});
	$("#test").click(function(){
		alert($("#sido_code option:selected").text());
		alert($("#sigoon_code option:selected").text());
		alert($("#dong_code option:selected").text());
		alert($("#detail").val());
		
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