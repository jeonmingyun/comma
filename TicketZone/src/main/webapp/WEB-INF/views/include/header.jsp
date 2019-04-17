<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Document</title>

<link href="../resources/css/header.css" rel="stylesheet" />

<link rel="stylesheet" href="<c:url value="/resources/css/css/bootstrap.min.css"/>">
<script src="../resources/js/header.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/js/bootstrap.min.js"/>"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#"><img id="mainLogo" src="../resources/img/mainLogo.png"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/store">제휴매장<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/inquiry">고객센터</a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link disabled" href="/mngr">관리자</a>
      </li>
    </ul>
  </div>
</nav>


</body>
</html>
