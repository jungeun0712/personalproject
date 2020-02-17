<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
    
<%-- /WebContent/model2/board/list.jsp--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Q&A 게시물 목록 보기</title>
<link rel="stylesheet" href="../../css/main.css">
<script type="text/javascript">
   function listdo(page) {
	   document.sf.pageNum.value=page;
	   document.sf.submit();
   }
</script>
</head>
<body>
<form action="list5.do" method="post" name="sf">
<input type="hidden" name="pageNum" value="1">
<input type="hidden" name="num" value="${q.num }">
</form>
<table><caption>Q&A 질문 목록</caption>
<c:if test="${boardcnt == 0}"> 
 <tr><td colspan="5">등록된 게시글이 없습니다.</td></tr>
</c:if> 
<c:if test="${boardcnt > 0}"> 
<tr><td colspan="5" style="text-align:right">글개수:${boardcnt}</td></tr>
 <tr><th width="8%">번호</th><th width="50%">제목</th><th width="20%">날짜</th>
<c:forEach var="q" items="${list}">
 <tr><td>${boardnum}</td>
 <c:set var="boardnum" value="${boardnum - 1}" />
 <td><a href="info5.do?num=${q.num}">${q.question}</a></td>
 <td>${q.today}</td>
</c:forEach>
 <tr><td colspan="5">
      <c:if test="${pageNum <= 1 }">[이전]</c:if>
      <c:if test="${pageNum > 1 }">      
          <a href="javascript:listdo(${pageNum - 1})">[이전]</a>
      </c:if>
      <c:forEach var="a" begin="${startpage}" end="${endpage}">
         <c:if test="${a==pageNum}">[${a}]</c:if>
         <c:if test="${a!=pageNum}">
           <a href="javascript:listdo(${a})">[${a}]</a>
         </c:if>
      </c:forEach>
      <c:if test="${pageNum >= maxpage }">[다음]</c:if>
      <c:if test="${pageNum < maxpage }">
        <a href="javascript:listdo(${pageNum + 1})">[다음]</a>
      </c:if>
 </td></tr>  
</c:if>
  <tr><td colspan="5" style="text-align:right">
     <a href="writeForm5.do">[글쓰기]</a></td></tr>
</table></body></html>