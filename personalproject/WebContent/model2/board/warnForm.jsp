<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>신고 목록 보기</title>
<link rel="stylesheet" href="../../css/main.css">
<script type="text/javascript">
   function listdo(page) {
	   document.sf.pageNum.value=page;
	   document.sf.submit();
   }
</script>
</head>
<body>
<form action="warnForm.do" method="post" name="sf">
<input type="hidden" name="pageNum" value="1">
<input type="hidden" name="seq" value="${w.seq}">
</form>
<table><caption>신고 질문 목록</caption>
<c:if test="${boardcnt == 0}"> 
 <tr><td colspan="5">등록된 게시글이 없습니다.</td></tr>
</c:if> 
<c:if test="${boardcnt > 0}"> 
<tr><td colspan="5" style="text-align:right">글개수:${boardcnt}</td></tr>
  <tr><th width="8%">번호</th><th width="20%">신고자</th><th width="20%">신고된 사람</th>
  <th width="40%">신고이유</th><th width="10%"></th>
<c:forEach var="w" items="${list}">
 <tr><td>${boardnum}</td>
 <td>${w.id}</td>
<td><a href="../member/list.do">${w.warnid}</a></td>
 <td>${w.reason}</td>
 <td><a href="warndelete.do?seq=${w.seq}">[삭제]</a></td>
 <c:set var="boardnum" value="${boardnum - 1}" />
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
</table></body></html>