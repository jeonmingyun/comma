<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>

<link href="resources/css/store.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="resources/js/store.js"></script>

</head>
<body>
	<%@include file="include/header.jsp"%>

	<div id="main">
	 <h2>제휴매장</h2>
     <div class="divider_1"><div class="line"></div></div>
            
		<div id="search_box">
			<input type="hidden" id="sido_key" value="12685d425f1af0872d756c" />
			<input type="hidden" id="sigoon_key" value="b0888bae39fbd0463a9252" />
			<input type="hidden" id="dong_key" value="91afccaa8d7f499151ee3b" />
			<!--  아직 key 인증을 받지 못함... -->
			<input type="hidden" name="apiKey"
				value="E4A59B05-0CF4-3654-BD0C-A169F70CCB34" />
			<!-- <form id='actionForm' action='/storeSearch' method='get'> -->
			<select id="sido_code" name="sido">
				<option>선택</option>
			</select> <select id="sigoon_code" name="sigoon">
				<option>선택</option>
			</select>
			<button id="test">검색</button>
			<!-- </form> -->
		</div>
		<!--search_box -->
		<div id="divider_sh">
			<div class="line"></div>
		</div>

		<div id="store_form">
			<c:forEach var="store" items="${list}" varStatus="status">
				<div id="store_row">
					<button id="button_img" class="license"
						value="${store.license_number}">
						<img class="s_img" name="s_img${status.index}"
							style="width: 250px; height: 200px;">
					</button>

					<div id="store_info">
						<h4>${store.store_name}</h4>
						<p>연락처: ${store.store_tel}</p>
						<p style="white-space: nowrap;">주소: ${store.address_name}</p>
						<p>영업시간: ${store.store_time}</p>

						<input type="hidden" name="path" class="uuid${status.index}"
							value="${store.img_uuid}"> <input type="hidden"
							name="path" class="filename${status.index}"
							value="${store.img_filename}"> <input type="hidden"
							name="path" class="uploadpath${status.index}"
							value="${store.img_uploadpath}">
					</div>
				</div>
				<!-- store_row -->
			</c:forEach>
			<c:if test="${empty list}">
			
				<p>검색결과가없습니다.</p>
			
			</c:if>		
		</div>
		<!-- store_form-->


		<!-- Paging -->
		<div class='pull-right'>
			<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li class="paginate_button previous"><a
						href="${pageMaker.startPage -1}"><</a></li>
				</c:if>

				<c:forEach var="num" begin="${pageMaker.startPage}"
					end="${pageMaker.endPage}">
					<li class="paginate_button"><a href="${num}">${num}</a></li>
				</c:forEach>

				<c:if test="${pageMaker.next}">
					<li class="paginate_button next"><a
						href="${pageMaker.endPage +1 }">></a></li>
				</c:if>
			</ul>
		</div>
		<form id='actionForm' action='/store' method='get'>
			<input type="hidden" id="efg" name="pageNum"
				value="${pageMaker.cri.pageNum}"> <input type="hidden"
				id="abcd" name="amout" value="${pageMaker.cri.amount}"> <input
				type="hidden" name="type"
				value='<c:out value="${pageMaker.cri.type}"/>'> <input
				type="hidden" name="keyword"
				value='<c:out value="${pageMaker.cri.keyword}"/>'> <input
				type="hidden" name="sido" value="${param.sido}"> <input
				type="hidden" name="sigoon" value="${param.sigoon}">
		</form>
	<!-- pull_right -->
	<!-- end Pagination -->
	</div>
	<!-- main -->
	<img alt="" src="/resource/img/2019/04/15/cde4247d-ab1c-4b9b-9426-214dcf0c7659_맘스터치.jpg">
	<%@include file="include/footer.jsp"%>
	
</body>
</html>

