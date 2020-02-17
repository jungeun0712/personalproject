<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/member/idForm.jsp --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>아이디찾기</title>
<link rel="stylesheet" href="../../css/main.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<form action="id.do" method="post" class="w3-container w3-card-4 w3-light-grey w3-text-black w3-margin">
<h2 class="w3-center">아이디 찾기</h2>
<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"></div>
    <div class="w3-rest">
      이름 <input class="w3-input w3-border" name="name" type="text">
    </div>
</div>

<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"></div>
    <div class="w3-rest">
      이메일 <input class="w3-input w3-border" name="email" type="text">
    </div>
</div>
<div class="w3-center">
	 <input type="submit" class="w3-button w3-blue-grey w3-round-xxlarge" value="아이디찾기">
 </div>
</form>
</body>
</html>