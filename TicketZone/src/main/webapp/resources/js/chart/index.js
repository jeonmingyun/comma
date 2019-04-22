google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
var the_number;
var ticket_reg;
var test = [];
$(document).ready(function(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1;
	var yyyy = today.getFullYear();
	if(dd<10){
		dd = '0' + dd
	}
	if(mm<10){
		mm = '0' + mm
	}
	today = yyyy+'/'+mm+'/'+dd;
	console.log(today);
	$.ajax({
		type: "post",
		url : "chart",
		dataType : "json",
		data : {today},
		success : function(data) {
			console.log(data);
			test[0] = [ 'Time', '대기인원', '대기팀' ];
			
			for(var i=1; i<=data.length; i++){
				test[i] = [data[i].ticket_reg.trim(), data[i].the_number, parseInt(data[i].team)];
				console.log(data[i].ticket_reg);
			}
			console.log(test);
		}, error : function(data){
			console.log("error");
		} 
	});
});


function drawChart() {
	var data = google.visualization
			.arrayToDataTable(test);
	
	var options = {
		title : 'Company Performance',
		curveType : 'function',
		legend : {
			position : 'bottom'
		}
	};

	var chart = new google.visualization.LineChart(document
			.getElementById('curve_chart'));

	chart.draw(data, options);
}
