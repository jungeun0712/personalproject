<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��� ȭ��</title>
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
<h2 class="w3-center">�α���</h2>
 <div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"></div>
    <div class="w3-rest">
      ���̵� <input class="w3-input w3-border" name="id" type="text">
    </div>
</div>

<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"></div>
    <div class="w3-rest">
      ��й�ȣ <input class="w3-input w3-border" name="pass" type="password">
    </div>
</div>
<div class="w3-center">
	 <input type="submit" class="w3-button w3-blue-grey w3-round-xxlarge" value="�α���">
     <input type="button" class="w3-button w3-blue-grey w3-round-xxlarge" value="ȸ������"
        onclick="location.href='joinForm.jsp'">
     <input type="button" class="w3-button w3-blue-grey w3-round-xxlarge" value="���̵�ã��" onclick="win_open('idForm')">
     <input type="button" class="w3-button w3-blue-grey w3-round-xxlarge" value="��й�ȣã��" onclick="win_open('pwForm')">
 </div></form></body></html>