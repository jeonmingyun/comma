<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>번호요</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<!-- 기존의 CSS, JS -->
<link href="resources/css/home.css" rel="stylesheet" />
<script src="resources/js/home.js"></script>


<!-- Bootstrap core CSS -->
<link href="../resources/bootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../resources/bootstrap/css/modern-business.css" rel="stylesheet">

</head>
<body>

<!-- header -->
<%@include file="include/header.jsp"%>
	
<!-- Page Header -->
  <!-- <header class="masthead" style="background-image: url('resources/bootstrap/img/home-bg.jpg')">
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
  </header> -->
  
  <header>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
      <div class="carousel-inner" role="listbox">
        <!-- Slide One - Set the background image for this slide in the line below -->
        <div class="carousel-item active" style="background-image: url('http://placehold.it/1900x1080')">
          <div class="carousel-caption d-none d-md-block">
            <h3>First Slide</h3>
            <p>This is a description for the first slide.</p>
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
             			<i class="fa fa-twitter"><img src="../resources/icon/번호표1.png" width="60px" height="60px"></i>
             		</div>
             		<div class="col-sm-9">
             			<h3>스마트 번호표 발급</h3>
                  	<p>
                  		어디서든 매장의 대기 정보를 확인 가능하고, 매장 근처에서 Bluetooth로 간편하게 번호표 발급이 가능합니다.
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
                  		대기시간을 자유롭게 사용하고 번호표 순번이 임박할 때 PUSH / SMS 알림을 받을 수 있습니다.
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
                  		카테고리별로 한식, 일식, 중식, 양식 등 검색이 가능하며 근처 매장을 GPS를 통해 알려줍니다.
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
                  		일 별, 시간대별로 매장을 이용하는 고객들의 수를 그래프로 제공합니다.
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
		<c:if test="${!empty sessionScope.admin}">
		<div id="writebutton">
		<!-- play store button div-->
		<button id="noticeWrite" onclick="noticeWrite()">글쓰기</button>
		</div>
		</c:if>	
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
