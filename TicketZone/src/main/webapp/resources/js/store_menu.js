$(document).ready(function(){
	
$(".catebtn").click(function(){
       var license_number = $("input[name='license_number']").val();
       var store_name = $("#s_name").val();
       console.log(license_number);
       var menu_cate = $(this).val();
       var query = {
             menu_cate : menu_cate,
             license_number : license_number
       }
       
        $.ajax({
          type: 'post',
          url: 'store_getListTocate',
          data: query,
          success: function(data){
             var menu_cate = data
             console.log(data);
             $(location).attr('href','/goMenu?license_number='+license_number+'&menu_cate='+menu_cate+'&store_name='+store_name);
          }            
       });          
    });
$("#goMenu").click(function(){
	location.reload();
});
$("#goChart").click(function(){
	var store_name = $("#s_name").val();
	var license_number = $("#license_number").val();
	$(location).attr('href', "/store_chart?store_name="+store_name+"&license_number="+license_number);
});
$("#goInfo").click(function(){
	var query = $("#license_number").val();	
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
   
    