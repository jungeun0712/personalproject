<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /WebContent/model2/member/info.jsp--%>   
<!DOCTYPE html>
<html><head><meta charset="EUC-KR">
<title>ȸ��������ȸ</title>
<link rel="stylesheet" href="../../css/main.css"></head>
<body>
<table><caption>ȸ�� ���� ����</caption>
  <tr><th>���̵�</th><td>${info.id}</td></tr>
   <tr><th>�̸�</th><td>${info.name}</td></tr>
   <tr><th>��ȭ</th><td>${info.tel}</td></tr>
   <tr><th>�̸���</th><td>${info.email}</td></tr>
   <tr><td colspan="2">
     <a href="updateForm.do?id=${info.id}">[����]</a>
     <c:if test="${info.id !='admin' && sessionScope.login != 'admin'}">
        <a href="deleteForm.do?id=${info.id}">[Ż��]</a>
     </c:if>
   </td></tr>
</table></body></html>