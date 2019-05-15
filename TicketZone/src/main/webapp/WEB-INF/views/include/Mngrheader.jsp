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
<!-- <link href="resources/css/header.css" rel="stylesheet"/> -->

<!-- Bootstrap core CSS -->
  <link href="../resources/bootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../resources/bootstrap/css/modern-business.css" rel="stylesheet">

<script>
	$(document).ready(function(){
		$("#log_out").click(function(){
			window.location.href="/mngrLogout";
		});
		
		$("#mainLogo").click(function(){
			window.location.href="/mngrOnly";
		})
	});
</script>

</head>
<body>
	<%-- <header>
		<!-- 로고 -->
		<img id="mainLogo" src="resources/img/mainLogo.png">

		<ul class="main-nav">
		<c:if test="${!empty store}">
			<li><a href="/mCustomer?license_number=${store[0].license_number}">고객관리</a></li>
		</c:if>
		
		<c:if test="${empty store}">
			<li><a href="/mCustomer?license_number=${param.license_number}">고객관리</a></li>
		</c:if>	
		
			<li><a href="/mInquiry">건의사항</a></li>
		</ul>
		
		<!-- 로그아웃 -->
		<button id="log_out">로그아웃</button>
	</header> --%>
	
	<!-- Navigation -->
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
  <div class="container">
    <a class="navbar-brand" href="/mngrOnly"><img id="mainLogo" src="../resources/img/mainLogo.png" height="40px;"></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
      <c:if test="${!empty store}">
        <li class="nav-item">
          <a class="nav-link" href="/mCustomer?license_number=${store[0].license_number}">고객관리</a>
        </li>
      </c:if>
      
      <c:if test="${empty store}">
        <li class="nav-item">
          <a class="nav-link" href="/mCustomer?license_number=${param.license_number}">고객관리</a>
        </li>
      </c:if>
      
        <li class="nav-item">
          <a class="nav-link" href="/mInquiry">건의사항</a>
          
        <!-- 로그아웃 -->
		<li><button id="log_out">로그아웃</button>
      </ul>
    </div>
  </div>
</nav>
	
<!-- Bootstrap core JavaScript -->
<script src="../resources/bootstrap/vendor/jquery/jquery.min.js"></script>
<script src="../resources/bootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	

</body>
</html>
