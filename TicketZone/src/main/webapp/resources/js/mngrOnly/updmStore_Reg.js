$(document).ready(function() {
	// '취소'버튼을 눌렀을 때 뒤로가기
	$("#cancel").click(function() {
		
		history.go(-1);
		return false; // 오류방지
	});
	
//	$("#update").click(function(){
//		// 매장이름
//		var store_name = $("input[name=store_name]").val();
//		// 업종
//		var cate_name = $("input[name=cate_code]").val();
//		// 대표자이름
//		var r_name = $("input[name=r_name]").val();
//		// 매장 전화번호
//		var store_tel = $("input[name=store_tel]").val();
//		// 영업시간
//		var store_time = $("input[name=store_time]").val();
//		// 매장소개
//		var store_intro = $("input[name=store_intro]").val();
//		// 매장주소
//		var address = $("input[name=address_code]").val();
//	});
});

