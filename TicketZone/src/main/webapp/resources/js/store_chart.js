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
	var t_sum = 0;
	var w_sum = 0;
	console.log(today);
	var query = {today:today};
	$.ajax({
		type: "post",
		url : "chart",
		dataType : "json",
		data : query,
		success : function(data) {			
			for(i=0; i<data.length; i++){
				test[i] = {
						ticket_reg : data[i].ticket_reg.trim() + '시',
						인원수 : data[i].the_number
				};
				t_sum += data[i].team;
				w_sum += data[i].the_number;				
			}
			console.log(test);
			str += "<button id='prev'><</button>&nbsp&nbsp";
			str += today;
			str += "&nbsp&nbsp<button id='next'>></button>";
			$("#today").append(str);
			$("#To").attr('value', today);
			$("#Actoday").attr('value', today);
			console.log(t_sum + "널?");
			var team = "";			
			team += t_sum +"팀";			
			var waiting = "";			
			waiting += w_sum +"명";			
			$("#waiting").append(waiting);
			$("#team").append(team);
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
	
	$("#goMenu").click(function(){
		var license_number = $("#license_number").val();
		var store_name = $("#s_name").val();
		$(location).attr('href',"/goMenu?license_number="+license_number+"&store_name="+store_name);
	});
	$("#goChart").click(function(){
		location.reload();
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
	
	$(document).on("click", '#prev', function(){	
		var today = $("#Actoday").val();
		var yy = $("#Actoday").val();
		
		$.ajax({
			type: "post",
			url : "/store_chartDel",
			dataType : "json",
			data : {today:today,
					day : yy.substr(2)
					},
			success : function(data) {	
				$("#Actoday").attr('value',('20' + data[0].today.substr(2)));
				$("#today").empty();
				$("#myfirstchart").empty();
				$("#team").empty();
				$("#waiting").empty();
				var sum = 0;
				var sum2 = 0;
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
					sum += data[i].the_number;
					sum2 += data[i].team;
					
				}
				var team = "";
				team += "<h4>팀</h4>";
				team += sum2 +"팀";
				
				var waiting = "";
				waiting += "<h4>대기자</h4>";
				waiting += sum +"명";
				
				$("#waiting").append(waiting);
				$("#team").append(team);
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
			url : "/store_chartAdd",
			dataType : "json",
			data : {today:today,
					day : yy.substr(2)
					},
			success : function(data) {
				
				$("#Actoday").attr('value',('20' + data[0].today.substr(2)));
				$("#today").empty();
				$("#myfirstchart").empty();
				$("#team").empty();
				$("#waiting").empty();
				var sum = 0;
				var sum2 = 0;
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
					sum += data[i].the_number;
					sum2 += data[i].team;
				}
				var team = "";
				team += "<h4>팀</h4>";
				team += sum2 +"팀";
				
				var waiting = "";
				waiting += "<h4>대기자</h4>";
				waiting += sum +"명";
				
				$("#waiting").append(waiting);
				$("#team").append(team);
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