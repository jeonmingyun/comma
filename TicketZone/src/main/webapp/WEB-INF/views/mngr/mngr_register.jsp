<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link href="../resources/css/mngr_register.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="resources/js/mngr_join.js"></script>
</head>
<body>
	
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<section>
        <div class="join_form">
            <h2>관리자 등록</h2>
            <div class="divider_1"><div class="line"></div></div>
            
            <div id="divider_sh">
				<div class="line"></div>
			</div>
            
            <form action="/register" method="post">
            
            <div class="join_box">
            <table class="join_tab">
            	<tbody>
            		<tr>
            			<td>관리자 아이디</td>
            			<td><input type="text" id="owner_id" name="owner_id"></td>
            		</tr>
            		
            		<tr>
            			<td>비밀번호</td>
            			<td><input type="password" id="owner_password" name="owner_password"></td>
            		</tr>
            		
            		<tr>
            			<td>비밀번호 확인</td>
            			<td><input type="password" name="mngr_pw_re"></td>
            		</tr>
            		
            		<tr>
            			<td>이름</td>
            			<td><input type="text" id="owner_name" name="owner_name"></td>
            		</tr>
            		
            		<tr>
            			<td>전화번호</td>
            			<td><input type="tel" id="owner_tel" name="owner_tel"></td>
            		</tr>
            		
            		<tr>
            			<td>이메일</td>
            			<td><input type="email" id="email" name="email"></td>
            		</tr>
            	</tbody>
            </table>
            	<div class="btn_box">
		            <input type="submit" class="join_btn join_ok" name="join_success" value="등록">
		            <button type="button" class="join_btn join_no" name="join_cancel" id="join_cancel">취소</button>
	            </div>
       		</div>
       </form>
            <!-- <button class="join_btn join_no" name="join_cancel" id="join_cancel">취소</button> -->
    </div>


    </section>
	<%-- <%@include file="/WEB-INF/views/include/footer.jsp"%> --%>


	

</body>
</html>
