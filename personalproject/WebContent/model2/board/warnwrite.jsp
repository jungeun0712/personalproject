<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/board/writeForm2.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Ű��ϱ�</title>
<script type="text/javascript" 
   src="http://cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
<script type="text/javascript">
   function inputcheck() {
	   f = document.f;
	   if(f.id.value=="") {
		   alert("���̵� �Է��ϼ���");
		   f.id.focus();
		   return;
	   }
	   if(f.warnid.value=="") {
		   alert("�Ű��һ���� �Է��ϼ���");
		   f.warnid.focus();
		   return;
	   }
	   if(f.reason.value=="") {
		   alert("�Ű����� �Է��ϼ���");
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
   <caption>�Ű��ϱ�</caption>
   <tr><th>�Ű���</th><td>${sessionScope.login}</td></tr>
   <tr><th>�Ű��� ���</th><td>${param.warnid}</td></tr>
   <tr><th>�Ű� ����</th><td><input type="text" name="reason"></td></tr>
   <tr><td colspan="2"><input type="submit" class="w3-button w3-blue-grey w3-round-xxlarge" value="�Ű��ϱ�"></td></tr>
  </table></form></body></html>