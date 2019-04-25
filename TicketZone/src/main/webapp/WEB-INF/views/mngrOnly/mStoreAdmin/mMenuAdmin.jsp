<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title></title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
p	{
	margin: 5px 0px;
}
	#customAdmin {
	width: 800px;
    border-collapse: collapse;
    text-align: center;	
    font-size: 16px;
    margin:20px auto;
}
/* 표 제목 */
#customAdmin th {
	color: #ffffff;
	background-color: #000000;
	border-bottom: 1px solid #000000;
	padding: 5px;
	font-size: 18px;
}
#customAdmin th:first-child {
  border-top-left-radius:5px;
}

#customAdmin th:last-child {
  border-top-right-radius:5px;
}

#customAdmin td {
    border-bottom: 1px solid #444444;
    padding: 10px;
}

/* 게시글 제목 클릭했을 때 */
#customAdmin tr:hover td{
	cursor: pointer;
	background-color: #EBEBEB;
}
</style>
<script>
	
	$(document).ready(function(){
		
		//메뉴 전체 선택 해제
		$("#selectAll").click(function(){		
			if($("#selectAll").prop("checked")){
				$("input[name='selectMenu']").prop("checked", true);
			} else {
				$("input[name='selectMenu']").prop("checked", false);
			}
		});
		
		//선택한 메뉴 수정창 만들어주기
		$("#UpdateInputMenu").click(function(){
			
			var tdArr = [];
			var checkbox = $("input[name='selectMenu']:checked");
			
			checkbox.each(function(i){
				var tr = checkbox.parent().parent().eq(i);
				var td = tr.children();
				var str = "";
				//값찾는과정
				var menu_name = td.eq(1).text();
				var menu_price = td.eq(2).text();
				var store_note = td.eq(3).text();
				//값을 비우는과정				
				td.eq(1).empty();
				td.eq(2).empty();
				td.eq(3).empty();
				//값을 추가하는 과정								
				td.eq(1).append("<input type='text' id='store_note' value='"+menu_name.trim()+"' style='text-align:left'>");
				td.eq(2).append("<input type='text' id='store_note' value='"+menu_price.trim()+"' style='text-align:left'>");
				td.eq(3).append("<input type='text' id='store_note' value='"+store_note.trim()+"' style='text-align:left'>");
				
										
			});
			$("#UpdateMenu").show();
			
		});
		
		//수정된내용 수정처리하기
		$("#UpdateMenu").click(function(){
			var license_number = $("input[name='license_number']").val();
			var tdArr = [];
			var checkbox = $("input[name='selectMenu']:checked");
			
			checkbox.each(function(i){
				var tr = checkbox.parent().parent().eq(i);
				var td = tr.children();
				var str = "";
				//값찾는과정
				var menu_code = td.eq(0).find("input[type='checkbox']").val();
				var menu_name = td.eq(1).find("input[type='text']").val();
				var menu_price = td.eq(2).find("input[type='text']").val();
				var store_note = td.eq(3).find("input[type='text']").val();
				var menu_cate = $("input[name='menu_cate']").val();
				
				tdArr[i] = {
						menu_code : menu_code,
						menu_name : menu_name,
						menu_price : menu_price,
						store_note : store_note,
						menu_cate : menu_cate
				};
				
										
			});
			
			$.ajax({
				type: 'post',
				url: 'updateMenu',
				data: JSON.stringify(tdArr),
				contentType: 'application/json',
				success: function(data){
					var menu_cate = data
					console.log(data);
					$(location).attr('href','/mMenuAdmin?license_number='+license_number+'&menu_cate='+menu_cate); 
				}		
			});
			
			
		});
		$("#CateInputUpdate").click(function(){
			$(".categorie").show();
			$("#UpdateCate").show();
		});
		
		/* $(".categorie").change(function(){
			var test = $(".catebtn").eq(this.value);
			var add = $(".Tab").eq(this.value);
			test.remove();
			add.append("<input type='text' size=5>");
			
			
			
		}); */
		
		$("#DeleteMenu").click(function(){
			var tdArr = [];
			var checkbox = $("input[name='selectMenu']:checked");
			var license_number = $("input[name='license_number']").val();
			
			checkbox.each(function(i){
				var tr = checkbox.parent().parent().eq(i);
				var td = tr.children();
				var menu_code = td.eq(0).find("input[type='checkbox']").val();
				var menu_cate = $("input[name='menu_cate']").val();
				tdArr[i] = {menu_code : menu_code,
							menu_cate : menu_cate}
							
				});
					$.ajax({
						type: 'post',
						url: 'deleteMenu',
						data: JSON.stringify(tdArr),
						contentType: 'application/json',
						success: function(data){
							var menu_cate = data
							console.log(data);
							$(location).attr('href','/mMenuAdmin?license_number='+license_number+'&menu_cate='+menu_cate); 
						}		
				});						
			});
			
		
		$(".catebtn").click(function(){
			var license_number = $("input[name='license_number']").val();
			console.log(license_number);
			var menu_cate = $(this).val();
			var query = {
					menu_cate : menu_cate,
					license_number : license_number
			}
			
			 $.ajax({
				type: 'post',
				url: 'getListTocate',
				data: query,
				success: function(data){
					var menu_cate = data
					console.log(data);
					$(location).attr('href','/mMenuAdmin?license_number='+license_number+'&menu_cate='+menu_cate);
				}				
			}); 			
		});
		
		$("#addSetMenu").click(function(){
			
			
			var id = "test";
			var item = $("#setMenu").val();
			var rowItem = "<p>";
			rowItem += "<button class=aa>"+item+"</button></p>"
			$("#menuTab").append(rowItem);
			$(".aa").attr('id', id);
			
			
			
		});
		
		$(document).on("click", "#test", function() {
			var license_number = $("input[name='license_number']").val();
			var query = {menu_cate : $("#test").text()}			
			
			$.ajax({
				type:'post',
				url:'loadCate',
				data:query,
				success: function(data){
					console.log(data);
					var menu_cate = data;
					$(location).attr('href','/insertMenu?license_number='+license_number+'&menu_cate='+menu_cate);
					
				}
			});
		});
		
		
		$("#addMenu").click(function(){
			var rowItem = "<tr>";
			rowItem += "<td> </td>"
			rowItem += "<td> <input type='text' class='add_name' name='menu_name'></td>";
			rowItem += "<td> <input type='text' class='add_price' name='menu_price'></td>";
			rowItem += "<td> <textarea class='add_store_note' name='store_note' style='width:500px'></textarea></td>";
			$("#customAdmin").append(rowItem);
			$("#TraddMenu").show();
		});
		
		$(document).on("click", "#TraddMenu", function() {
			var query = [];
			
			for ( var i = 0; i < $(".add_name").length; i++){
				var	menu_cate = $("input[name='menu_cate']").val();
				var	menu_name = $(".add_name").eq(i).val();
				var	menu_price = $(".add_price").eq(i).val();
				var	store_note = $(".add_store_note").eq(i).val();
				var	license_number = $("input[name='license_number']").val();
				var count = i+1			
				query[i] = {
					menu_cate:menu_cate,
					menu_name:menu_name,
					menu_price:menu_price,
					store_note:store_note,
					license_number:license_number,
					count : count
				}
			}
						
			 $.ajax({
				type: 'post',
				url: 'insertAccess',
				data: JSON.stringify(query),
				contentType: 'application/json',
				success: function(data){
					var menu_cate = data
					console.log(data);
					$(location).attr('href','/mMenuAdmin?license_number='+license_number+'&menu_cate='+menu_cate); 
				}		
			}); 
			
		});
		
		
	});
</script>
</head>
<body>
<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
<%@include file="/WEB-INF/views/include/storeAdmin.jsp" %>
<section>
<div id="menu">
<h2>메뉴 리스트 보기</h2>
리스트 추가하기:<input type="text" id="setMenu" name="setMenu"><button id="addSetMenu">분류 추가하기</button>
<div id="menuTab" style="border:1px solid; display:inline-block; float:left; margin-top:45px; height:240px; width:100px; text-align:center;">		
		<p style="border-bottom:1px solid;">분류</p>
		<c:if test="${empty tab}"></c:if>		
		<c:if test="${!empty tab}">
			<c:forEach var="c" items="${tab}" varStatus = "status">
				<p class="Tab"><input type="checkbox" class="categorie" value="${status.index}" style="display:none;"><button class="catebtn" name="menu_cate" value="${c.menu_cate}">${c.menu_cate}</button>
				</p>
			</c:forEach>
		</c:if>	
			<c:if test="${!empty add}">
				<p class="Tab"><button class="addcate" name="menu_cate" value="${add}">${add}</button></p>
			</c:if>
							
		</div>
			
		
		
		<div id="menuList" style="display:inline-block; text-align:center; float:left;">
		<button id="CateInputUpdate">탭 수정하기</button>
		<button id="addMenu">메뉴 추가하기</button>
		<button id="UpdateInputMenu">수정</button>
		<button id="DeleteMenu">삭제</button>	
		<c:if test="${empty menu}">
			<table id="customAdmin">
				<tr>
					<th>메뉴이름
					<th>가격
					<th>설명</th>
				</tr>
				
			</table>	
		</c:if>
		<c:if test="${!empty menu}">
			<table id="customAdmin">
				<tr>
					<th><input type="checkbox" id="selectAll">선택
					<th>메뉴이름
					<th>가격
					<th>설명</th>
				</tr>
				<c:forEach var="m" items="${menu}">
				<tr>
					<td><input type="checkbox" class="selectMenu" name="selectMenu" value="${m.menu_code}">
					<td id="menu_name">${m.menu_name}
					<td id="menu_price">${m.menu_price}
					<td id="store_note">${m.store_note}
				</tr>
				</c:forEach>				
			</table>
		</c:if>
			
			<button id="TraddMenu" style="display:none">추가하기</button>		
			<button id="UpdateMenu" style="display:none">수정하기</button>
			<button id="UpdateCate" style="display:none">탭 수정하기</button>
		 </div>
		 		 
</div>
<c:if test="${!empty param.license_number}">			
<input type="hidden" name="license_number" value="${param.license_number}">
</c:if>
<c:if test="${empty param.license_number}">			
<input type="hidden" name="license_number" value="${tab[0].license_number}">
</c:if>
<c:if test="${!empty param.menu_cate}">
<input type="hidden" name="menu_cate" value="${param.menu_cate}">
</c:if>
<c:if test="${empty param.menu_cate}">
<input type="hidden" name="menu_cate" value="${tab[0].menu_cate}">
</c:if>

</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>