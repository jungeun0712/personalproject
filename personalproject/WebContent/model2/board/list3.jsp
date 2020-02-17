<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
    
<%-- /WebContent/model2/board/list.jsp--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��� �Խ��� ��� ����</title>
<link rel="stylesheet" href="../../css/main.css">
<script type="text/javascript">
   function listdo(page) {
	   document.sf.pageNum.value=page;
	   document.sf.submit();
   }
</script>
</head>
<body>
<form action="list3.do" method="post" name="sf">
<input type="hidden" name="pageNum" value="1">
<input type="hidden" name="type" value="3">
<input type="hidden" name="id" value='${param.id}'>
<table><tr><td style="border-width: 0px;">
<select name="column"><option value="">�����ϼ���</option>
    <option value="subject">����</option>
    <option value="content">����</option>
    <option value="subject,content">����+����</option></select>
<script>document.sf.column.value = "${param.column}";</script>    
<input type="text" name="find" value="${param.find}" style="width:50%;">
<input type="submit" value="�˻�"></td>
</tr></table></form>
<table><caption>��� �Խ��� ���</caption>
<c:if test="${boardcnt == 0}"> 
 <tr><td colspan="5">��ϵ� �Խñ��� �����ϴ�.</td></tr>
</c:if> 
<c:if test="${boardcnt > 0}"> 
<tr><td colspan="5" style="text-align:right">�۰���:${boardcnt}</td></tr>
 <tr><th width="8%">��ȣ</th><th width="14%">Ű����</th><th width="30%">����</th>
 <th width="15%">�����</th><th width="8%">��ȸ��</th>
<c:forEach var="b" items="${list}">
 <tr><td>${boardnum}</td>
 <c:set var="boardnum" value="${boardnum - 1}" />
 <c:if test="${b.keyword == 1}"> 
 <td>���</td>
</c:if> 
 <c:if test="${b.keyword == 2}"> 
 <td>�б�</td>
</c:if> 
 <c:if test="${b.keyword == 3}"> 
 <td>�ΰ�����</td>
</c:if> 
 <c:if test="${b.keyword == 4}"> 
 <td>��Ÿ</td>
</c:if> 
 <td style="text-align: center">     
    <a href="info3.do?num=${b.num}&pageNum=${pageNum}&type=${b.type}">${b.subject}</a>
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
 </td><td>${b.readcnt}</td></tr>    
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
     <a href="writeForm3.do?type=3">[�۾���]</a></td></tr>
</table></body></html>