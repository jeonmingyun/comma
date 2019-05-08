$(document).ready(function(){	
	var today = new Date();
	var dd = today.getDate()-1;	
	var mm = today.getMonth()+1;
	var yyyy = today.getFullYear();
	var week = new Array('일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일');
	var day = week[today.getDay()];
	console.log(week[today.getDay()]);
	var test = [];
	var json;
	if(dd<10){
		dd = '0' + dd
	}
	if(mm<10){
		mm = '0' + mm
	}
	today = yyyy+'/'+mm+'/'+dd;
	var str = "";
	
	console.log(today);
	$.ajax({
		type: "post",
		url : "chart",
		dataType : "json",
		data : {today},
		success : function(data) {			
			for(i=0; i<data.length; i++){
				test[i] = {
						ticket_reg : data[i].ticket_reg.trim() + '시',
						인원수 : data[i].the_number
				};
			}
			
			str += "<button id='prev'><</button>&nbsp&nbsp";
			str += today;
			str += "&nbsp&nbsp<button id='next'>></button>";
			$("#today").append(str);
			$("#To").attr('value', today);
			$("#Actoday").attr('value', today);
			var obj = JSON.stringify(test);
			json = JSON.parse(obj);
			new Morris.Line({
				  // ID of the element in which to draw the chart.
				  element: 'myfirstchart',
				  // Chart data records -- each entry in this array corresponds to a point on
				  // the chart.
				  data: json,
				  // The name of the data record attribute that contains x-values.
				  xkey: 'ticket_reg',
				  // A list of names of data record attributes that contain y-values.
				  ykeys: ['인원수'],
				  // Labels for the ykeys -- will be displayed when you hover over the
				  // chart.
				  labels: ['인원수'],
				  parseTime:false
				
				});
		}, error : function(data){
			console.log("error");
		} 
	});
	$(document).on("click", '#prev', function(){
		
		
		var today = $("#Actoday").val();
		var yy = $("#Actoday").val();
		
		$.ajax({
			type: "post",
			url : "/chartDel",
			dataType : "json",
			data : {today:today,
					day : yy.substr(2)
					},
			success : function(data) {	
				$("#Actoday").attr('value',('20' + data[0].today.substr(2)));
				$("#today").empty();
				$("#myfirstchart").empty();
				var a = "";
				a += "<button id='prev'><</button>&nbsp&nbsp"				
				a += '20' + data[0].today.substr(2);
				a += "&nbsp&nbsp<button id='next'>></button>";
				$("#today").append(a);
				test = [];
				for(i=0; i<data.length; i++){
					test[i] = {
							ticket_reg : data[i].ticket_reg.trim() + '시',
							인원수 : data[i].the_number
					};
				}		
				var obj = JSON.stringify(test);
				json = JSON.parse(obj);
				new Morris.Line({
					  // ID of the element in which to draw the chart.
					  element: 'myfirstchart',
					  // Chart data records -- each entry in this array corresponds to a point on
					  // the chart.
					  data: json,
					  // The name of the data record attribute that contains x-values.
					  xkey: 'ticket_reg',
					  // A list of names of data record attributes that contain y-values.
					  ykeys: ['인원수'],
					  // Labels for the ykeys -- will be displayed when you hover over the
					  // chart.
					  labels: ['인원수'],
					  parseTime:false
					
					});
			}
		});
				
	});
	
	$(document).on("click", '#next', function(){
		
		
		var today = $("#Actoday").val();
		var nextDay = $("#To").val();
		var yy = $("#Actoday").val();		
		 if(nextDay == today){
			alert("데이터가 아직 집계되지않았습니다.");
			return false;
		}  
		
		console.log("뭐야?");
		
		$.ajax({
			type: "post",
			url : "/chartAdd",
			dataType : "json",
			data : {today:today,
					day : yy.substr(2)
					},
			success : function(data) {
				
				$("#Actoday").attr('value',('20' + data[0].today.substr(2)));
				$("#today").empty();
				$("#myfirstchart").empty();
				var a = "";
				a += "<button id='prev'><</button>&nbsp&nbsp"				
				a += '20' + data[0].today.substr(2);
				a += "&nbsp&nbsp<button id='next'>></button>";
				$("#today").append(a);
				test = [];
				for(i=0; i<data.length; i++){
					test[i] = {
							ticket_reg : data[i].ticket_reg.trim() + '시',
							인원수 : data[i].the_number
					};
				}		
				var obj = JSON.stringify(test);
				json = JSON.parse(obj);
				new Morris.Line({
					  // ID of the element in which to draw the chart.
					  element: 'myfirstchart',
					  // Chart data records -- each entry in this array corresponds to a point on
					  // the chart.
					  data: json,
					  // The name of the data record attribute that contains x-values.
					  xkey: 'ticket_reg',
					  // A list of names of data record attributes that contain y-values.
					  ykeys: ['인원수'],
					  // Labels for the ykeys -- will be displayed when you hover over the
					  // chart.
					  labels: ['인원수'],
					  parseTime:false
					
					});
				
			}
		});
	});
});