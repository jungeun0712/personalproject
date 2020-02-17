<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /WebContent/model2/member/info.jsp--%>   
<!DOCTYPE html>
<html><head><meta charset="EUC-KR">
<title>회원정보조회</title>
<link rel="stylesheet" href="../../css/main.css"></head>
<body>
<table><caption>회원 정보 보기</caption>
  <tr><th>아이디</th><td>${info.id}</td></tr>
   <tr><th>이름</th><td>${info.name}</td></tr>
   <tr><th>전화</th><td>${info.tel}</td></tr>
   <tr><th>이메일</th><td>${info.email}</td></tr>
   <tr><td colspan="2">
     <a href="updateForm.do?id=${info.id}">[수정]</a>
     <c:if test="${info.id !='admin' && sessionScope.login != 'admin'}">
        <a href="deleteForm.do?id=${info.id}">[탈퇴]</a>
     </c:if>
   </td></tr>
</table></body></html>