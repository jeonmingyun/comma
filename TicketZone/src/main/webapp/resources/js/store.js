
		
$(document).ready(function() {
	var actionForm = $("#actionForm");
	   $(".paginate_button a").on("click", function(e){
	      e.preventDefault();
	      
	      actionForm.find("input[name='pageNum']").val($(this).attr("href"));
	      actionForm.submit();
	   });   
	
	$(".license").each(function(i,e){
		uuid = $(".uuid"+i+"").val();
		console.log(uuid);
		filename = $(".filename"+i+"").val();
		console.log(filename);
		uploadpath = $(".uploadpath"+i+"").val();
		console.log(uploadpath);
		//변수분리
		var filePath = encodeURIComponent(uploadpath+"/" + uuid +"_"+filename);
		var a = $("img[name=s_img"+i+"]").attr("src", "/display?fileName="+filePath);
		
	});
			
	
			
	$("#test").on("click", function(e){
		var a = $("#sido_code option:selected").text();
		var b = $("#sigoon_code option:selected").text();
			if(a.length == 5){
				a = a.substr(0,2);
			} else if(a.length == 4){
				a = a.substr(0,1) + a.substr(-2,1);
				
			} else if (a.length == 3){
				a = a.substr(0,2);
			} else {
				a = a;
			}
		var pageNum = $("#efg").val();
		var amount = $("#abcd").val();
		var query = {
				sido : a,
				sigoon : b,
				pageNum : pageNum,
				amount : amount
		};
		$.ajax({
			url : '/storSearch',
			data : query,
			dataType : 'text',
			type : 'POST',
			success:function(data){
				$(location).attr("href", "store?sido="+a+"&sigoon="+b);
			}
		});
	});	
	
//	var searchForm = $("#searchForm");
//	$("#searchForm button").on("click", function(e){
//		e.preventDefault();
//		searchForm.find("input[name='pageNum']").val("1");
//		searchForm.submit();
//	});
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
     $("#test").click(function(){
 		
 		
 			
     });
});
	
		
	
	