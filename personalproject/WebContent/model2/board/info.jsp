<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /WebContent/model2/board/info.jsp--%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�ϱ��� �� ����</title>
<link rel="stylesheet" href="../../css/main.css" >
</head>
<body>
<table><caption>�ϱ��� �� ����</caption>
<tr><th>����</th>
  <td style="text-align:left">${b.subject}</td></tr>
<tr><th>����</th>
  <td><table style="width:100%; height:250px;"><tr>
  <td style="border-width:0px; vertical-align:top; text-align:left">
  ${b.content}</td></tr></table>
  </td></tr>
<tr><th>÷������</th>
<td>&nbsp;<c:if test="${!empty b.file1 }">
 <a href="file/${b.file1}">${b.file1}</a>
  </c:if></td></tr>
<tr><td colspan="2">
  <a href="updateForm.do?id=${b.id}&num=${b.num}&pageNum=${param.pageNum}&type=${b.type}">[����]</a>
  <a href="deleteForm.do?id=${b.id}&num=${b.num}&pageNum=${param.pageNum}&type=${b.type}">[����]</a>
  <a href="list.do?pageNum=${param.pageNum}&id=${b.id}&type=${b.type}">[���]</a>  
</td></tr></table></body></html>