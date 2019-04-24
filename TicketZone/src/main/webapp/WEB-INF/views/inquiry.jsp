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
<link href="resources/css/inquiry.css" rel="stylesheet" />
<script src="resources/js/inquiry.js"></script>
</head>
<body>

   <div>
      <%@include file="include/header.jsp"%>

      <section>
         <div id="suggest_form">
         <!-- 문의하기 -->
            <h1>문의하기</h1>
            <div id="inquiry_table">
            <form id="searchForm" method="get" action="/inquiry">
               <select name="type">
                  <option value="T">제목</option>
                  <option value="C">내용</option>
                  <option value="CA">유형</option>                  
                  <option value="TC">제목 or 내용</option>
               </select> <input type="text" name="keyword" />
               <input type="hidden" id="efg" name="pageNum" value="${pageMaker.cri.pageNum}">
               <input type="hidden" id="abcd" name="amout" value="${pageMaker.cri.amount}">
               <button class="btn-default">검색</button>
            </form>
            
            <table id="suggest">
               <tr>
                  <th style="width:5%;">번호</th>
                  <th style="width:20%;">문의유형</th>
                  <th style="width:30%;">제목</th>
                  <th style="width:10%;">작성자</th>
                  <th style="width:20%;">작성일</th>
               </tr>
               <c:forEach var="sl" items="${suggestList}">
                  <tr onclick="showInquiry(${sl.board_no})">
                     <td>${sl.board_no}
                     <td>${sl.cate_name}
                     <td>${sl.board_title}<c:if
                           test="${!empty sl.board_password}">
                           <img src="resources/icon/baseline-lock-24px.svg" alt="locked"
                              height="15" width="15" />
                        </c:if>
                     <td>${sl.member_tel}
                     <td><fmt:formatDate value="${sl.board_reg}" pattern="yyyy.MM.dd"/>
                  </tr>
               </c:forEach>
            </table>
            <div id="writebutton">
            <button type="button" id="btnwrite">글쓰기</button>
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
   <form id='actionForm' action='/inquiry' method='get'>
      <input type="hidden" id="efg" name="pageNum" value = "${pageMaker.cri.pageNum}">
      <input type="hidden" id="abcd" name="amout" value = "${pageMaker.cri.amount}">
      <input type="hidden" name="type" value='<c:out value="${pageMaker.cri.type}"/>'>
      <input type="hidden" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"/>'>
   </form>
   <!-- end Pagination -->
      </section>

      <%@include file="include/footer.jsp"%>
   </div>
</body>
</html>