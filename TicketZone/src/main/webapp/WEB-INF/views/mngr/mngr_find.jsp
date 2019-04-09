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
<link href="../resources/css/mngr_find.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="resources/js/mngr_find.js"></script>

</head>
<body>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<section>
        <div class="find_form">
            <h1>관리자 아이디 찾기</h1>
            
            <div><!-- 휴대폰 본인인증 -->
            <input type="radio" class="id_find" id="phone" name="id_find" value="phone_num" checked="checked">
            
            <label for="phone">휴대폰 본인인증</label>
                <!-- 휴대폰 본인인증 라디오 버튼 클릭했을 때 -->
                <div id="phone_ok">
                    <p>이름</p>
                    <input type="text" id="owner_name" name="owner_name">
                    <p>전화번호 ('-'꼭 기입 해 주세요.)</p>
                    <input type="tel" id="owner_tel" name="owner_tel">  
                    <input type="submit" class="find_btn" name="find_success" value="찾기">
                    <div id="findfail"></div>       
                </div>
            </div>

            <div><!-- 이메일 본인인증 -->
            <input type="radio" class="id_find" id="e_mail" name="id_find" value="e_mail">
            <label for="e_mail">이메일 본인인증</label>
                
                <!-- 이메일 본인인증 라디오 버튼 클릭했을 때  -->
                <!-- <div id="email_ok" style="display:none;"></div> -->
                    
                        <!-- <label for="e_name">이름</label>
                        <p>이름</p>
                        <input type="text" id="e_name" required>
                         <label for="e_email">이메일</label>
                        <p>이메일</p>
                        <input type="email" id="e_email" required>
                        <button>인증 메일 받기</button>
                        <input type="email" id="e_email" placeholder="인증번호 6자리" required>
                        -->
                    <dl id="email_ok" style="display:none;">
                        <dt><label for="e_name" class="label_txt">이름</label></dt>
                        <dd><input type="text" id="e_name" required></dd>
                                
                        <dt><label for="e_email" class="label_txt">이메일</label></dt>
                        <dd><input type="email" id="e_email" required>
                        <button class="ok_btn">받기</button></dd>
                            
                        <dt><label for="e_email" class="txt_none">인증번호</label></dt>
                        <dd><input type="text" id="e_email" placeholder="인증번호 6자리" required></dd>
                    </dl>
                <!-- </div>  -->
            </div>
        </div>
    </section>
    <%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>