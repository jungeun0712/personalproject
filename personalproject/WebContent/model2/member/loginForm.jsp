<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인 화면</title>
<link rel="stylesheet" href="../../css/main.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script type="text/javascript">
   function win_open(page) {
	   var op = "width=500, height=350, left=50,top=150";
	   open(page+".do","",op);
   }
</script>
</head>
<body>
<form action="login.do" name="f" method="post" class="w3-container w3-card-4 w3-light-grey w3-text-black w3-margin">
<h2 class="w3-center">로그인</h2>
 <div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"></div>
    <div class="w3-rest">
      아이디 <input class="w3-input w3-border" name="id" type="text">
    </div>
</div>

<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"></div>
    <div class="w3-rest">
      비밀번호 <input class="w3-input w3-border" name="pass" type="password">
    </div>
</div>
<div class="w3-center">
	 <input type="submit" class="w3-button w3-blue-grey w3-round-xxlarge" value="로그인">
     <input type="button" class="w3-button w3-blue-grey w3-round-xxlarge" value="회원가입"
        onclick="location.href='joinForm.jsp'">
     <input type="button" class="w3-button w3-blue-grey w3-round-xxlarge" value="아이디찾기" onclick="win_open('idForm')">
     <input type="button" class="w3-button w3-blue-grey w3-round-xxlarge" value="비밀번호찾기" onclick="win_open('pwForm')">
 </div></form></body></html>