<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/board/writeForm3.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խ��� �۾���</title>
<script type="text/javascript" 
   src="http://cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
<script type="text/javascript">
   function inputcheck() {
	   f = document.f;
	   if(f.ckeckpass.value=="") {
		   alert("��й�ȣ�� �Է��ϼ���");
		   f.pass.focus();
		   return;
	   }
	   if(f.subject.value=="") {
		   alert("������ �Է��ϼ���");
		   f.subject.focus();
		   return;
	   }
	   if(f.content.value=="") {
		   alert("������ �Է��ϼ���");
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
   <caption>��� �Խ��� �۾���</caption>
   <tr><th>Ű����</th><td><input type="radio" name="keyword" value="1">��� <input type="radio" name="keyword" value="2">�б� 
   <input type="radio" name="keyword" value="3">�ΰ����� <input type="radio" name="keyword" value="4">��Ÿ</td></tr>
   <tr><th>����</th><td><input type="text" name="subject"></td></tr>
   <tr><th>��й�ȣ</th><td><input type="password" name="checkpass"></td></tr>
   <tr><th>����</th><td><textarea rows="15" name="content" id="content1"></textarea></td></tr>
   <tr><td colspan="2"><input type="submit" class="w3-button w3-blue-grey w3-round-xxlarge" value="�Խù����"></td></tr>
  </table></form></body></html>