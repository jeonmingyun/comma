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