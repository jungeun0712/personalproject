<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /WebContent/model2/board/updateForm.jsp--%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Q&A 질문 수정 화면</title>
<link rel="stylesheet" href="../../css/main.css">
<script>
  function file_delete() {
	  document.f.file2.value="";
	  file_desc.style.display="none";
  }
</script>
</head>
<body>
<form action="update5.do" method="post" name="f">
   <input type="hidden" name="num" value="${param.num}">
   <table><caption>Q&A 질문 수정 화면</caption>
   <tr><td>비밀번호</td><td>
   <input type="password" name="pw" value="${q.pw}"></td></tr>
   <tr><td>질문</td><td>
   <input type="text" name="question" value="${q.question}"></td></tr>
   <tr><td colspan="2">
   <a href="javascript:document.f.submit()">[게시물수정]</a></td></tr>
   </table>   
</form></body></html>