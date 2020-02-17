<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /WebContent/model2/board/info5.jsp--%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Q&A 일기장 질문 상세 보기</title>
<link rel="stylesheet" href="../../css/main.css" >
</head>
<body>
<input type="hidden" name="num" value="${q.num}">
<input type="hidden" name="pw" value="${q.pw}">
<input type="hidden" name="subject" value="${q.question}">
<table><caption>Q&A 일기장 질문 상세 보기</caption>
<tr><th>질문</th>
  <td style="text-align:left">${q.question}</td></tr>
<tr><td colspan="2">
  <a href="updateForm5.do?num=${param.num}">[수정]</a>
  <a href="list5.do">[목록]</a>  
</td></tr></table></body></html>