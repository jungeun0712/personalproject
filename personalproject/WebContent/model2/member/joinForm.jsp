<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/member/joinForm.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 가입</title>
<link rel="stylesheet" href="../../css/main.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript">
   function checkValue(){
      f= document.f;
      if(f.name.value==""){
         alert("이름을 입력하세요");
         f.name.focus();
         return false;
      }
      if(f.id.value==""){
         alert("아이디를 입력하세요");
         f.id.focus();
         return false;
      }
      if(f.pass.value==""){
         alert("비밀번호를 입력하세요");
         f.pass.focus();
         return false;
      }
      if(f.email.value==""){
         alert("이메일을 입력하세요");
         f.email.focus();
         return false;
      }
      if(f.tel.value==""){
         alert("전화번호를 입력하세요");
         f.tel.focus();
         return false;
      }
   }
</script>
</head>
<body>
<form action="join.do" name="f" method="post" class="w3-container w3-card-4 w3-light-grey w3-text-blue-grey w3-margin"
onsubmit="return checkValue()">
<h2 class="w3-center">회원가입</h2>
 <div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
    <div class="w3-rest">
      <input class="w3-input w3-border" name="name" type="text" placeholder="이름">
    </div>
</div>

<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
    <div class="w3-rest">
      <input class="w3-input w3-border" name="id" type="text" placeholder="아이디">
    </div>
</div>

<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-pencil"></i></div>
    <div class="w3-rest">
      <input class="w3-input w3-border" name="pass" type="password" placeholder="비밀번호">
    </div>
</div>

<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-envelope-o"></i></div>
    <div class="w3-rest">
      <input class="w3-input w3-border" name="email" type="text" placeholder="Email">
    </div>
</div>

<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-phone"></i></div>
    <div class="w3-rest">
      <input class="w3-input w3-border" name="tel" type="text" placeholder="전화번호">
    </div>
</div>
<div class="w3-center">
<button type="submit" value="회원가입" class="w3-button w3-blue-grey w3-round-xxlarge">회원가입</button>
</div></form></body></html>