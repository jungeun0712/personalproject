<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/board/writeForm5.jsp --%>    
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
	   if(f.question.value=="") {
		   alert("������ �Է��ϼ���");
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
   <caption>Q&A �ϱ���  ���� ����</caption>
   <tr><th>��й�ȣ</th><td><input type="password" name="pw"></td></tr>
   <tr><th>����</th><td><input type="text" name="question"></td></tr>
   <tr><th>��ϳ�¥</th><td><input type="text" name="today" placeholder="ex)2019-12-05�� �Է�"></td></tr>
   <tr><td colspan="2"><input type="submit" class="w3-button w3-blue-grey w3-round-xxlarge" value="�Խù����"></td></tr>
  </table></form></body></html>