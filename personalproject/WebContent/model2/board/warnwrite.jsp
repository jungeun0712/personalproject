<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/board/writeForm2.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>신고하기</title>
<script type="text/javascript" 
   src="http://cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
<script type="text/javascript">
   function inputcheck() {
	   f = document.f;
	   if(f.id.value=="") {
		   alert("아이디를 입력하세요");
		   f.id.focus();
		   return;
	   }
	   if(f.warnid.value=="") {
		   alert("신고할사람을 입력하세요");
		   f.warnid.focus();
		   return;
	   }
	   if(f.reason.value=="") {
		   alert("신고내용을 입력하세요");
		   f.reason.focus();
		   return;
	   }
	   f.submit();
   }
</script>
</head>
<body>
<form action="warn.do" method="post" name="f" onsubmit="return inputcheck()">
<input type="hidden" name="num" value="${param.num}">
<input type="hidden" name="seq" value="${param.seq}">
<input type="hidden" name="id" value="${sessionScope.login}">
<input type="hidden" name="warnid" value="${param.warnid}">
  <table>
   <caption>신고하기</caption>
   <tr><th>신고자</th><td>${sessionScope.login}</td></tr>
   <tr><th>신고할 사람</th><td>${param.warnid}</td></tr>
   <tr><th>신고 내용</th><td><input type="text" name="reason"></td></tr>
   <tr><td colspan="2"><input type="submit" class="w3-button w3-blue-grey w3-round-xxlarge" value="신고하기"></td></tr>
  </table></form></body></html>