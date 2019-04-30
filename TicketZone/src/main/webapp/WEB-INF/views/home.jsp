<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>번호요</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="resources/css/home.css" rel="stylesheet" />
<script src="resources/js/home.js"></script>
<!-- Bootstrap core CSS -->
<link href="/resources/bootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="bootstrap/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

<!-- Custom styles for this template -->
<link href="/resources/bootstrap/css/clean-blog.min.css" rel="stylesheet">

</head>
<body>

	<%@include file="include/header.jsp"%>
	
	 <!-- Page Header -->
  <header class="masthead" style="background-image: url('resources/bootstrap/img/home-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="site-heading">
            <a href="https://play.google.com/store/apps/details?id=com.soonbuny">
				<img alt="no image" src="resources/img/playstore.png">
			</a>
            <span class="subheading">번호요 지금 바로 이용하세요</span>
          </div>
        </div>
      </div>
    </div>
  </header>
	
	<!-- 서비스 -->
    <div class="features-container section-container">
     <div class="container">
         <div class="main_row">
             <div class="main_service">
                 	<h2>Main Service</h2>
                 <div class="divider_1"><div class="line"></div></div>
             </div>
         </div>
         
         <div class="row">
             <div class="col-sm-6 features-box wow fadeInLeft">
             	<div class="row">
             		<div class="box_icon">
             			<i class="fa fa-twitter"><img src="../resources/icon/번호표.png" width="60px" height="60px"></i>
             		</div>
             		<div class="col-sm-9">
             			<h3>스마트 번호표 발급</h3>
                  	<p>
                  		Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et.
                  		Ut wisi enim ad minim veniam, quis nostrud.
                  	</p>
             		</div>
             	</div>
             </div>
             <div class="col-sm-6 features-box wow fadeInLeft">
             	<div class="row">
             		<div class="box_icon">
             			<i class="fa fa-instagram"><img src="../resources/icon/푸시.png" width="60px" height="60px"></i>
             		</div>
             		<div class="col-sm-9">
             			<h3>PUSH알림 서비스</h3>
                  	<p>
                  		Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et.
                  		Ut wisi enim ad minim veniam, quis nostrud.
                  	</p>
             		</div>
             	</div>
             </div>
         </div>
         
         <div class="row">
             <div class="col-sm-6 features-box wow fadeInLeft">
             	<div class="row">
             		<div class="box_icon">
             			<i class="fa fa-magic"><img src="../resources/icon/카테고리별.png" width="60px" height="60px"></i>
             		</div>
             		<div class="col-sm-9">
             			<h3>카테고리별 검색</h3>
                  	<p>
                  		Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et.
                  		Ut wisi enim ad minim veniam, quis nostrud.
                  	</p>
             		</div>
             	</div>
             </div>
             <div class="col-sm-6 features-box wow fadeInLeft">
             	<div class="row">
             		<div class="box_icon">
             			<i class="fa fa-cloud"><img src="../resources/icon/웨이팅.png" width="60px" height="60px"></i>
             		</div>
             		<div class="col-sm-9">
             			<h3>실시간 웨이팅 현황</h3>
                  	<p>
                  		Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et.
                  		Ut wisi enim ad minim veniam, quis nostrud.
                  	</p>
             		</div>
             	</div>
             </div>
         </div>
     </div>
    </div>
	
	<!-- 공지사항 div -->
	<div id="home">
		<h2>Notice</h2>
		<div class="divider_1"><div class="line"></div></div>
		<div id="notice_table">
		<form id="searchForm" method="get" action = "/">
		<select name="type">
			<option value="T">제목</option>
			<option value="C">내용</option>
			<option value="TC">제목 or 내용</option>
		</select>
		<input type="text" name="keyword" />
		<input type="hidden" id="efg" name="pageNum" value = "${pageMaker.cri.pageNum}">
		<input type="hidden" id="abcd" name="amout" value = "${pageMaker.cri.amount}">
		<button class="btn-default">검색</button>
		</form>

		<table id="notice">
			<tr>
				<th style="width:5%;">번호</th>
				<th style="width:30%;">제목</th>
				<th style="width:10%;">작성자</th>
				<th style="width:20%;">날짜</th>
			</tr>
		 	<c:forEach var="nl" items="${noticeList}">
				<tr onclick="showNotice(${nl.notice_no})">
					<td>${nl.notice_no}
					<td>${nl.notice_title}
					<td>관리자
					<td><fmt:formatDate value="${nl.notice_reg}" pattern="yyyy.MM.dd"/>
				</tr>
			</c:forEach>			
		</table>
		<div id="writebutton">
		<!-- play store button div-->
		<button id="noticeWrite" onclick="noticeWrite()">글쓰기</button>
		</div>
		</div>
	</div>
	
	<!-- Paging -->
	<div class='pull-right'>
		<ul class="pagination">
			<c:if test="${pageMaker.prev}">
				<li class="paginate_button previous"><a href="${pageMaker.startPage -1}"><</a></li>
			</c:if>
			
			<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
				<li class="paginate_button"><a href="${num}">${num}</a></li>
			</c:forEach>
			
			<c:if test="${pageMaker.next}">
				<li class="paginate_button next"><a href="${pageMaker.endPage +1 }">></a></li>
			</c:if>
		</ul>
	</div>
	<form id='actionForm' action='/' method='get'>
		<input type="hidden" id="efg" name="pageNum" value = "${pageMaker.cri.pageNum}">
		<input type="hidden" id="abcd" name="amout" value = "${pageMaker.cri.amount}">
		<input type="hidden" name="type" value='<c:out value="${pageMaker.cri.type}"/>'>
		<input type="hidden" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"/>'>
	</form>
	<!-- end Pagination -->
	
	<footer>
		<%@include file="include/footer.jsp"%>
	</footer>

</body>
</html>
