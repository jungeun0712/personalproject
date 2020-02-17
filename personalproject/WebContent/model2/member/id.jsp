<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model1/member/id.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>id찾기</title>
<link rel="stylesheet" href="css/main.css">
<script type="text/javascript">
   function idsend(id) {
	   opener.document.f.id.value=id;
	   self.close();
   }
</script>
</head>
<body>
<table><tr><th>아이디</th>
    <td>${id}**</td></tr>
  <tr><td colspan="2">
     <input type="button" value="아이디전송" 
    onclick="idsend('${id}')"></td></tr>
</table></body></html>
