<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /WebContent/model2/member/updateForm.jsp--%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 수정 </title>
<link rel="stylesheet" href="../../css/main.css">
<script type="text/javascript">
    function win_passchg() {
 	   var op = "width=500, height=230, left=50,top=150";
 	   open("passwordForm.do","",op);
    }    
</script>
</head>
<body>
<form action="memupdate.do" name="f" method="post" 
       onsubmit="return inputcheck(this)">
<table><caption>회원 정보 수정</caption>
<tr><th>이름</th><td><input type="text" name="name" value="${info.name}"></td></tr>
<tr><th>아이디</th><td><input type="text" name="id" readonly  value="${info.id}"></td></tr>
<tr><th>비밀번호</th><td><input type="password" name="pass" ></td></tr>
<tr><th>전화번호</th><td><input type="text" name="tel" value="${info.tel}"></td></tr>
<tr><th>이메일</th><td><input type="text" name="email" value="${info.email}"></td></tr>
<tr><td colspan="3"><input type="submit" value="회원수정">
<c:if test="${sessionScope.login != 'admin' 
              || param.id == sessionScope.login}">
<input type="button" value="비밀번호수정" onclick="win_passchg()">
</c:if></td></tr>
</table></form></body></html>