<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /WebContent/model2/board/info2.jsp--%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Q&A �ϱ��� �� ����</title>
<link rel="stylesheet" href="../../css/main.css" >
</head>
<body>
<table><caption>Q&A �ϱ��� �� ����</caption>
<tr><th>����</th>
  <td style="text-align:left">${subject}</td></tr>
<tr><th>����</th>
  <td><table style="width:100%; height:250px;"><tr>
  <td style="border-width:0px; vertical-align:top; text-align:left">
  ${b.content}</td></tr></table>
  </td></tr>
<tr><td colspan="2">
  <a href="updateForm2.do?id=${b.id}&num=${b.num}&pageNum=${param.pageNum}&type=${b.type}">[����]</a>
  <a href="deleteForm2.do?id=${b.id}&num=${b.num}&pageNum=${param.pageNum}&type=${b.type}">[����]</a>
  <a href="list2.do?pageNum=${param.pageNum}&id=${b.id}&type=${b.type}">[���]</a>  
</td></tr></table></body></html>