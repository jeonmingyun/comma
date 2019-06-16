<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link href="resources/css/chart.css" rel="stylesheet" />
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery-json/2.6.0/jquery.json.min.js"></script>
</head>

<body>
	<%@include file="/WEB-INF/views/include/Mngrheader.jsp"%>
	
<div class="chart_form">
		<input type="hidden" id="select_license_number"
			value="${param.license_number}"> <select
			name="license_number" id="storelist">
			<c:forEach var="s" items="${store}" varStatus="status">
				<option class="storeList_option" value="${s.license_number}">
					${s.store_name}</option>
			</c:forEach>
		</select>
	
	<div id="today_stats">
		<a class="goChart">현황판</a>&nbsp&nbsp&nbsp&nbsp<a href="/chart2">일별 통계</a>
	</div>
	
	<h2>일별 시간대별 인원통계</h2>
	<div class="divider_1"><div class="line"></div></div>
	
	<div id="chart_box">
		<!-- chart -->
		<div id="chart">
			<div id="today" style="text-align: center;"></div>
			<div id="myfirstchart" style="height: 300px;"></div>
			<input type="hidden" id="Actoday" value="${param.today}">
			<input type="hidden" id="To">
		</div>
		
		<div id="team"> <!-- 팀 -->
			<h4>팀</h4>
		</div>
		
		<div id="waiting"> <!--대기자--> 
			<h4>대기자</h4> 
		</div>
	</div>	<!-- chart_form -->
</div>

	

<script src="resources/js/chart/index.js"></script>
</body>
</html>