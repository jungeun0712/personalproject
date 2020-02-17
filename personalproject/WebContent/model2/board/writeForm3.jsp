<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/board/writeForm3.jsp --%>    
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
	   if(f.ckeckpass.value=="") {
		   alert("비밀번호를 입력하세요");
		   f.pass.focus();
		   return;
	   }
	   if(f.subject.value=="") {
		   alert("제목을 입력하세요");
		   f.subject.focus();
		   return;
	   }
	   if(f.content.value=="") {
		   alert("내용을 입력하세요");
		   f.content.focus();
		   return;
	   }
	   f.submit();
   }
</script>
</head>
<body>
<form action="write3.do?id=${sessionScope.login}&type=3" method="post" name="f" onsubmit="return inputcheck()">
  <input type="hidden" name="id" value='${param.id}'>
  <input type="hidden" name="type" value="3">
  <table>
   <caption>고민 게시판 글쓰기</caption>
   <tr><th>키워드</th><td><input type="radio" name="keyword" value="1">취업 <input type="radio" name="keyword" value="2">학교 
   <input type="radio" name="keyword" value="3">인간관계 <input type="radio" name="keyword" value="4">기타</td></tr>
   <tr><th>제목</th><td><input type="text" name="subject"></td></tr>
   <tr><th>비밀번호</th><td><input type="password" name="checkpass"></td></tr>
   <tr><th>내용</th><td><textarea rows="15" name="content" id="content1"></textarea></td></tr>
   <tr><td colspan="2"><input type="submit" class="w3-button w3-blue-grey w3-round-xxlarge" value="게시물등록"></td></tr>
  </table></form></body></html>