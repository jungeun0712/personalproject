<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model1/board/replyForm.jsp--%>
<!DOCTYPE html>
<html><head><meta charset="EUC-KR">
<title>�Խ��� ��� ����</title>
<link rel="stylesheet" href="../../css/main.css"></head>
<body>
<form action="reply.do?num=${b.num}&pageNum=${param.pageNum}&type=3&id=${sessionScope.login}" method="post" name="f">
  <input type="hidden" name="num" value="${b.num}">
  <input type="hidden" name="id" value="${b.id}">
  <table><caption>�Խ��� ��� ���</caption>
  <tr><th>����</th>
      <td><textarea name="comment" rows="15"></textarea></td></tr>
  <tr><td colspan="2">
  <a href="javascript:document.f.submit()">[�亯�۵��]</a></td></tr>    
  </table>
</form></body></html>