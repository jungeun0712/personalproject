<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /WebContent/model2/board/info5.jsp--%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Q&A �ϱ��� ���� �� ����</title>
<link rel="stylesheet" href="../../css/main.css" >
</head>
<body>
<input type="hidden" name="num" value="${q.num}">
<input type="hidden" name="pw" value="${q.pw}">
<input type="hidden" name="subject" value="${q.question}">
<table><caption>Q&A �ϱ��� ���� �� ����</caption>
<tr><th>����</th>
  <td style="text-align:left">${q.question}</td></tr>
<tr><td colspan="2">
  <a href="updateForm5.do?num=${param.num}">[����]</a>
  <a href="list5.do">[���]</a>  
</td></tr></table></body></html>