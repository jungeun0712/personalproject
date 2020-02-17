<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /WebContent/model2/board/updateForm.jsp--%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>고민게시판 수정 화면</title>
<link rel="stylesheet" href="../../css/main.css">
<script>
  function file_delete() {
	  document.f.file2.value="";
	  file_desc.style.display="none";
  }
</script>
</head>
<body>
<form action="update3.do" method="post" name="f">
   <input type="hidden" name="id" value="${b.id}">
   <input type="hidden" name="num" value="${b.num}">
   <input type="hidden" name="type" value="${b.type}">
   <table><caption>고민 게시판 수정 화면</caption>
   <tr><td>키워드</td><td><input type="radio" name="keyword" value="1">취업 <input type="radio" name="keyword" value="2">학교 
   <input type="radio" name="keyword" value="3">인간관계 <input type="radio" name="keyword" value="4">기타</td></tr>
   <tr><td>비밀번호</td><td><input type="password" name="checkpass"></td></tr>
   <tr><td>제목</td><td>
   <input type="text" name="subject" value="${b.subject}"></td></tr>
   <tr><td>내용</td><td>
   <textarea rows="15" name="content" id="content1">${b.content}</textarea></td></tr>
   <tr><td colspan="2">
   <a href="javascript:document.f.submit()">[게시물수정]</a></td></tr>
   </table>   
</form></body></html>