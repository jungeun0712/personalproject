<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/board/deleteForm.jsp --%>    
<!DOCTYPE html>
<html><head>
<meta charset="EUC-KR">
<title>Q&A �ϱ��� ���� ȭ��</title>
<link rel="stylesheet" href="../../css/main.css">
</head>
<body>
<form action="delete2.do?id=${param.id}&num=${param.num}&pageNum=${param.pageNum}&type=${param.type}" name="f" method="post">
<input type="hidden" name="num"  value="${param.num}">
<table><caption>Q&A �ϱ��� ���� ȭ��</caption>
  <tr><td>�Խñۺ�й�ȣ</td>
      <td><input type="password" name="pass"></td></tr>
  <tr><td colspan="2">
     <input type="submit" value="�Խñۻ���"></td></tr>
</table></form></body></html>