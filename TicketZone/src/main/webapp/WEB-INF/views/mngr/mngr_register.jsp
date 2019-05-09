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
            <h1>관리자 등록</h1>
            <form action="/register" method="post">
            <p>관리자 아이디</p>
                <input type="text" id="owner_id" name="owner_id">
            <!-- 비밀번호 길이가 맞지 않을 때 "8~12자" 밑에 뜨게!
            아이디 중복일 경우, 아이디 사용 가능할 경우에도 밑에 뜨게-->
            <p>비밀번호</p>
                <input type="password" id="owner_password" name="owner_password">
            <!-- 비밀번호 길이가 맞지 않을 때 "8~16자 영문, 숫자" 밑에 뜨게!-->
            <p>비밀번호 확인</p>
                <input type="password" name="mngr_pw_re">
            <p>이름</p>
                <input type="text" id="owner_name" name="owner_name">
            <p>전화번호</p>
                <input type="tel" id="owner_tel" name="owner_tel">
            <p>이메일 <label>* 선택</label></p> 
                <input type="email" id="email" name="email">

            <input type="submit" class="join_btn" name="join_success" value="등록">
       </form>
            <button class="join_btn" name="join_cancel" id="join_cancel">취소</button>
        </div>


    </section>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>


	

</body>
</html>
