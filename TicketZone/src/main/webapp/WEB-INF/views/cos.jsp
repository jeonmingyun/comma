<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
<title>Document</title>

</head>
<body>

	<%@include file="include/header.jsp"%>
	<h1>라인차트 테스트</h1>
	<div id="lineChart" style="height: 250px;"></div>
	<script type="text/javascript">
		$(document).ready(function(){
			var monthList = '${chart}';
			
			
			
	new Morris.Line({
		  // ID of the element in which to draw the chart.
		  element: 'lineChart',
		  // Chart data records -- each entry in this array corresponds to a point on
		  // the chart.
		  data: monthList,
		  // The name of the data record attribute that contains x-values.
		  xkey: 'ticket_reg',
		  // A list of names of data record attributes that contain y-values.
		  ykeys: ['total'],
		  // Labels for the ykeys -- will be displayed when you hover over the
		  // chart.
		  labels: ['total']
		});
	});
	</script>
	
	

	<%@include file="include/footer.jsp"%>

</body>
</html>
