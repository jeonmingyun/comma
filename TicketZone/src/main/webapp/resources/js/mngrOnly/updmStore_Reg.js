$(document).ready(function() {
	var mapContainer = document.getElementById('map') // 지도를 표시할 div
	var coor_x = document.getElementById("coor_x").value
	var coor_y = document.getElementById("coor_y").value
	mapOption = {
		center : new daum.maps.LatLng(coor_y, coor_x), // 지도의 중심좌표
		level : 3
	// 지도의 확대 레벨
	};

	//지도를 미리 생성
	var map = new daum.maps.Map(mapContainer, mapOption);
	//주소-좌표 변환 객체를 생성
	var geocoder = new daum.maps.services.Geocoder();
	//마커를 미리 생성
	var marker = new daum.maps.Marker({
		position : new daum.maps.LatLng(coor_y, coor_x),
		map : map
	});	

	
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

function sample5_execDaumPostcode() {
	var mapContainer = document.getElementById('map')
	var map = new daum.maps.Map(mapContainer, mapOption);
	var geocoder = new daum.maps.services.Geocoder();
	var marker = new daum.maps.Marker({
		position : new daum.maps.LatLng(coor_y, coor_x),
		map : map
	});	
	new daum.Postcode({
		oncomplete : function(data) {
			var addr = data.address; // 최종 주소 변수

			// 주소 정보를 해당 필드에 넣는다.
			document.getElementById("sample5_address").value = addr;
			// 주소로 상세 정보를 검색
			geocoder.addressSearch(data.address, function(results, status) {
				// 정상적으로 검색이 완료됐으면

				if (status === daum.maps.services.Status.OK) {

					var result = results[0]; //첫번째 결과의 값을 활용
					console.log(result.x);
					console.log(result.y);
					document.getElementById("coor_x").value = result.x;
					document.getElementById("coor_y").value = result.y;
					// 해당 주소에 대한 좌표를 받아서
					var coords = new daum.maps.LatLng(result.y, result.x);
					// 지도를 보여준다.
					mapContainer.style.display = "block";
					map.relayout();
					// 지도 중심을 변경한다.
					map.setCenter(coords);
					// 마커를 결과값으로 받은 위치로 옮긴다.
					marker.setPosition(coords)
				}
			});
		}
	}).open();
}	