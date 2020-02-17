<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
    
<%-- /WebContent/model2/board/list.jsp--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<form action="list2.do" method="post" name="sf">
<input type="hidden" name="pageNum" value="1">
<input type="hidden" name="id" value='${param.id}'>
<input type="hidden" name="type" value="2">
<table><tr><td style="border-width: 0px;">
<select name="column"><option value="">선택하세요</option>
    <option value="subject">제목</option>
    <option value="regdate">등록일</option>
    <option value="subject,regdate">제목+등록일</option></select>
<script>document.sf.column.value = "${param.column}";</script>    
<input type="text" name="find" value="${param.find}" style="width:50%;">
<input type="submit" value="검색"></td>
</tr></table></form>
<table><caption>Q&A 일기장 목록</caption>
<c:if test="${boardcnt == 0}"> 
 <tr><td colspan="5">등록된 게시글이 없습니다.</td></tr>
</c:if> 
<c:if test="${boardcnt > 0}"> 
<tr><td colspan="5" style="text-align:right">글개수:${boardcnt}</td></tr>
 <tr><th width="8%">번호</th><th width="50%">제목</th><th width="14%">등록일</th>
<c:forEach var="b" items="${list}">
 <tr><td>${boardnum}</td>
 <c:set var="boardnum" value="${boardnum - 1}" />
 <td><a href="info2.do?id=${b.id}&num=${b.num}&pageNum=${pageNum}&type=${b.type}">${b.subject}</a></td>
 <td>
  <fmt:formatDate var="rdate" value="${b.regdate}" 
                                   pattern="yyyyMMdd" />
  <c:if test="${today == rdate}">
     <fmt:formatDate value="${b.regdate}" pattern="HH:mm:ss" />
  </c:if>
  <c:if test="${today != rdate}">
     <fmt:formatDate value="${b.regdate}"
                                  pattern="yy-MM-dd" />
  </c:if>
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
     <a href="writeForm2.do?type=2&subject=${subject}">[글쓰기]</a></td></tr>
</table></body></html>