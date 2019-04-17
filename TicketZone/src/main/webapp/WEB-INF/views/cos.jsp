<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<style>
#chartdiv {
  width: 100%;
  height: 500px;
}

</style>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<title>Document</title>
</head>
<body>
 <h1>일별 시간대별 현황</h1>
 <script src="resources/js/chart/index.js"></script>
  <div id="curve_chart" style="width: 900px; height: 500px"></div>
</body>
</html>
