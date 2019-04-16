$(document).ready(function(){
	$(".license_number").each(function(i,e){
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
	/*매장 등록 버튼 누르면 매장등록 페이지로 이동*/
	$(".store_register").click(function(){
		window.location.href = "/mStore_Register";			
	});
	
	/*매장 사진 클릭했을 때 수정페이지로 이동*/
	$(".license_number").click(function() {
		var num = $(".license_number").index(this); /*몇 번째인지*/
		var license_num = $(".license_number:eq("+ num + ")").val();
		window.location.href = "/updmStore_Register?license_number="+license_num;
	})
});