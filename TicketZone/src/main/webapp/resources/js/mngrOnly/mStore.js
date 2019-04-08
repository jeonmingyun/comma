$(document).ready(function(){
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