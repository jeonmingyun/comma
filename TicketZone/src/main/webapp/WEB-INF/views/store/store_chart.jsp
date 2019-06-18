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
<link href="../resources/css/store_chart.css" rel="stylesheet" />
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
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	
	<input type="hidden" id="s_name" value="${param.store_name}">
		<!-- <div class="store_form"> -->
		<div class="chart_form">	
    		<div id="tab_menu">
			<h2 id="store_name">${param.store_name}</h2>
				<nav>
					<ul class="menu">
					<li><button id="goMenu">메뉴</button></li>
					<li><button id="goInfo">매장정보</button></li>
					<li><button id="goChart">통계</button></li>
					</ul>
				</nav>
		</div>
		
		<div class="divider_sh">
			<div class="line"></div>
		</div>	
		
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
<input type="hidden" id="license_number" value="${param.license_number}">
</div> <!-- store_form -->

	

<script src="../resources/js/store_chart.js"></script>
</body>
</html>