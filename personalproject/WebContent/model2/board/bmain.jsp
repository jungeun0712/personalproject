<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%-- /WebContent/model2/member/main.jsp--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����������</title>
<link rel="stylesheet" href="../../css/main.css">
<script type="text/javascript">
   function listdo(page) {
	   document.sf.pageNum.value=page;
	   document.sf.submit();
   }
</script>
</head>
<body>
<form action="main.do" name="f" method="post">
<input type="hidden" name="pageNum" value="1">
<input type="hidden" name="id" value='${param.id}'>
</form>
<table><caption>�Խ��� ���</caption>
<c:if test="${boardcnt == 0}"> 
 <tr><td colspan="5">��ϵ� �Խñ��� �����ϴ�.</td></tr>
</c:if> 
<c:if test="${boardcnt > 0}"> 
<tr><td colspan="5" style="text-align:right">�۰���:${boardcnt}</td></tr>
 <tr><th width="8%">��ȣ</th><th width="50%">����</th><th width="14%">�����</th>
<c:forEach var="b" items="${list}">
 <tr><td>${boardnum}</td>
 <c:set var="boardnum" value="${boardnum - 1}" />
 <td style="text-align: left">
 <%-- ÷������ ǥ���ϱ� --%>
  <c:if test="${!empty b.file1}">      
    <a href="file/${b.file1}" style="text-decoration: none;">@</a>
  </c:if>
  <c:if test="${empty b.file1}">&nbsp;&nbsp;&nbsp;</c:if>       
    <a href="info.do?num=${b.num}&pageNum=${pageNum}">${b.subject}</a>
 </td>
 <td>
  <fmt:formatDate var="rdate" value="${b.regdate}" 
                                   pattern="yyyyMMdd" />
  <c:if test="${today == rdate}">
     <fmt:formatDate value="${b.regdate}" pattern="HH:mm:ss" />
  </c:if>
  <c:if test="${today != rdate}">
     <fmt:formatDate value="${b.regdate}"
                                  pattern="yy-MM-dd HH:mm" />
  </c:if>
</c:forEach>
 <tr><td colspan="5">
      <c:if test="${pageNum <= 1 }">[����]</c:if>
      <c:if test="${pageNum > 1 }">      
          <a href="javascript:listdo(${pageNum - 1})">[����]</a>
      </c:if>
      <c:forEach var="a" begin="${startpage}" end="${endpage}">
         <c:if test="${a==pageNum}">[${a}]</c:if>
         <c:if test="${a!=pageNum}">
           <a href="javascript:listdo(${a})">[${a}]</a>
         </c:if>
      </c:forEach>
      <c:if test="${pageNum >= maxpage }">[����]</c:if>
      <c:if test="${pageNum < maxpage }">
        <a href="javascript:listdo(${pageNum + 1})">[����]</a>
      </c:if>
 </td></tr>  
</c:if>
  <tr><td colspan="5" style="text-align:right">
     <a href="writeForm.do?id=${id}">[�۾���]</a></td></tr>
</table>
</body>
</html>