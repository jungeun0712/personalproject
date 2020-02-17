<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model1/board/replyForm.jsp--%>
<!DOCTYPE html>
<html><head><meta charset="EUC-KR">
<title>게시판 답글 쓰기</title>
<link rel="stylesheet" href="../../css/main.css"></head>
<body>
<form action="reply.do?num=${b.num}&pageNum=${param.pageNum}&type=3&id=${sessionScope.login}" method="post" name="f">
  <input type="hidden" name="num" value="${b.num}">
  <input type="hidden" name="id" value="${b.id}">
  <table><caption>게시판 답글 등록</caption>
  <tr><th>내용</th>
      <td><textarea name="comment" rows="15"></textarea></td></tr>
  <tr><td colspan="2">
  <a href="javascript:document.f.submit()">[답변글등록]</a></td></tr>    
  </table>
</form></body></html>