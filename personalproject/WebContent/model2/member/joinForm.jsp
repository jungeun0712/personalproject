<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/member/joinForm.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ�� ����</title>
<link rel="stylesheet" href="../../css/main.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript">
   function checkValue(){
      f= document.f;
      if(f.name.value==""){
         alert("�̸��� �Է��ϼ���");
         f.name.focus();
         return false;
      }
      if(f.id.value==""){
         alert("���̵� �Է��ϼ���");
         f.id.focus();
         return false;
      }
      if(f.pass.value==""){
         alert("��й�ȣ�� �Է��ϼ���");
         f.pass.focus();
         return false;
      }
      if(f.email.value==""){
         alert("�̸����� �Է��ϼ���");
         f.email.focus();
         return false;
      }
      if(f.tel.value==""){
         alert("��ȭ��ȣ�� �Է��ϼ���");
         f.tel.focus();
         return false;
      }
   }
</script>
</head>
<body>
<form action="join.do" name="f" method="post" class="w3-container w3-card-4 w3-light-grey w3-text-blue-grey w3-margin"
onsubmit="return checkValue()">
<h2 class="w3-center">ȸ������</h2>
 <div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
    <div class="w3-rest">
      <input class="w3-input w3-border" name="name" type="text" placeholder="�̸�">
    </div>
</div>

<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
    <div class="w3-rest">
      <input class="w3-input w3-border" name="id" type="text" placeholder="���̵�">
    </div>
</div>

<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-pencil"></i></div>
    <div class="w3-rest">
      <input class="w3-input w3-border" name="pass" type="password" placeholder="��й�ȣ">
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
      <input class="w3-input w3-border" name="tel" type="text" placeholder="��ȭ��ȣ">
    </div>
</div>
<div class="w3-center">
<button type="submit" value="ȸ������" class="w3-button w3-blue-grey w3-round-xxlarge">ȸ������</button>
</div></form></body></html>