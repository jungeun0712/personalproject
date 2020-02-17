<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /WebContent/model2/board/info3.jsp--%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>고민게시판 상세 보기</title>
<link rel="stylesheet" href="../../css/main.css" >
</head>
<body>
<table><caption>고민게시판 상세 보기</caption>
<tr><th>키워드</th>
<c:if test="${b.keyword == 1}"> 
 <td>취업</td>
</c:if> 
 <c:if test="${b.keyword == 2}"> 
 <td>학교</td>
</c:if> 
 <c:if test="${b.keyword == 3}"> 
 <td>인간관계</td>
</c:if> 
 <c:if test="${b.keyword == 4}"> 
 <td>기타</td>
</c:if></tr>
<tr><th>제목</th>
  <td style="text-align:left">${b.subject}</td></tr>
<tr><th>내용</th>
  <td><table style="width:100%; height:250px;"><tr>
  <td style="border-width:0px; vertical-align:top; text-align:left">
  ${b.content}</td></tr></table>
  </td></tr>
<tr><td colspan="2">
<c:if  test="${b.id == sessionScope.login}">
  <a href="updateForm3.do?num=${b.num}&pageNum=${param.pageNum}&type=${b.type}">[수정]</a>
  <a href="deleteForm3.do?num=${b.num}&pageNum=${param.pageNum}&type=${b.type}">[삭제]</a>
</c:if>
  <a href="list3.do?pageNum=${param.pageNum}&type=${b.type}">[목록]</a>  
</td></tr></table>

<form action="reply.do?type=3&num=${b.num}&id=${sessionScope.login}" method="post" name="f">
  <input type="hidden" name="num" value="${b.num}">
  <input type="hidden" name="id" value="${b.id}">
  <input type="hidden" name="type" value="${b.type}">
  <table><caption>고민 게시판 답글</caption>
  <tr><th>아이디</th>
  <td style="text-align:left">${sessionScope.login}</td></tr>
  <tr><th>답글</th>
      <td><textarea name="comment"></textarea></td></tr>
  <tr><td colspan="2">
  <input type="submit" class="w3-button w3-blue-grey w3-round-xxlarge" value="답글등록"></td></tr>   
  </table>
</form>

<input type="hidden" name="num" value="${param.num}" >
<c:forEach var="c" items="${c}">
<table>
 <tr><th>아이디</th><td>${c.id} <a href="warnwrite.do?seq=${c.seq}&num=${b.num}&warnid=${c.id}">신고</a></td></tr>
 <tr><th>댓글</th><td>${c.comment}</td></tr><br>
</table>
</c:forEach>
</body></html>

