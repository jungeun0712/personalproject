<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/board/writeForm.jsp --%>    
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
	   if(f.checkpass.value=="") {
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
<form action="write.do?id=${sessionScope.login}&type=1" method="post" 
       enctype="multipart/form-data" name="f" onsubmit="return inputcheck()">
  <input type="hidden" name="id" value='${param.id}'>
  <input type="hidden" name="type" value="1">
  <table>
   <caption>�ϱ� ����</caption>
   <tr><th>����</th><td><input type="text" name="subject"></td></tr>
   <tr><th>��й�ȣ</th><td><input type="password" name="checkpass"></td></tr>
   <tr><th>����</th><td><textarea rows="15" name="content" id="content1"></textarea></td></tr>
   <tr><th>÷������</th><td><input type="file" name="file1"></td></tr>
   <tr><td colspan="2"><input type="submit" class="w3-button w3-blue-grey w3-round-xxlarge" value="�Խù����"></td></tr>
  </table></form>
  </body></html>