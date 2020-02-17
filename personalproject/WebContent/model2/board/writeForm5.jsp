<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/board/writeForm5.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 글쓰기</title>
<script type="text/javascript" 
   src="http://cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
<script type="text/javascript">
   function inputcheck() {
	   f = document.f;
	   if(f.question.value=="") {
		   alert("내용을 입력하세요");
		   f.content.focus();
		   return;
	   }
	   f.submit();
   }
</script>
</head>
<body>
<form action="write5.do" method="post" name="f" onsubmit="return inputcheck()">
  <table>
   <caption>Q&A 일기장  질문 쓰기</caption>
   <tr><th>비밀번호</th><td><input type="password" name="pw"></td></tr>
   <tr><th>질문</th><td><input type="text" name="question"></td></tr>
   <tr><th>등록날짜</th><td><input type="text" name="today" placeholder="ex)2019-12-05로 입력"></td></tr>
   <tr><td colspan="2"><input type="submit" class="w3-button w3-blue-grey w3-round-xxlarge" value="게시물등록"></td></tr>
  </table></form></body></html>