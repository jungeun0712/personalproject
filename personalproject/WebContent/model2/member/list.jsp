<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /WebContent/model2/member/list.jsp--%>    
<!DOCTYPE html>
<html><head>
<meta charset="EUC-KR">
<title>회원목록보기</title>
<link rel="stylesheet" href="../../css/main.css">
</head>
<body>
<table><caption>회원 목록</caption>
  <tr><th>아이디</th><th>이름</th><th>전화번호</th>
      <th>이메일</th><th>&nbsp;</th></tr>
<c:forEach var="m" items="${list}">
  <tr><td><a href="info.do?id=${m.id}">${m.id}</a></td>
      <td>${m.name}</td>
      <td>${m.tel}</td>
      <td>${m.email}</td>
      <td><a href="updateForm.do?id=${m.id}">[수정]</a>
     <c:if test="${m.id != 'admin'}">
          <a href="delete.do?id=${m.id}">[강제탈퇴]</a>
     </c:if></td></tr>
</c:forEach>
</table></body></html>