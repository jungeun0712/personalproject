<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /WebContent/model2/board/updateForm.jsp--%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Q&A �ϱ��� ���� ȭ��</title>
<link rel="stylesheet" href="../../css/main.css">
<script>
  function file_delete() {
	  document.f.file2.value="";
	  file_desc.style.display="none";
  }
</script>
</head>
<body>
<form action="update2.do" method="post" name="f">
   <input type="hidden" name="id" value="${b.id}">
   <input type="hidden" name="num" value="${b.num}">
   <input type="hidden" name="type" value="${b.type}">
   <table><caption>Q&A �Խ��� ���� ȭ��</caption>
   <tr><td>��й�ȣ</td><td><input type="password" name="checkpass"></td></tr>
   <tr><td>����</td><td>
   <input type="text" name="subject" value="${b.subject}"></td></tr>
   <tr><td>����</td><td>
   <textarea rows="15" name="content" id="content1">${b.content}</textarea></td></tr>
   <tr><td colspan="2">
   <a href="javascript:document.f.submit()">[�Խù�����]</a></td></tr>
   </table>   
</form></body></html>