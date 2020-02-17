<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /WebContent/model2/board/info.jsp--%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>일기장 상세 보기</title>
<link rel="stylesheet" href="../../css/main.css" >
</head>
<body>
<table><caption>일기장 상세 보기</caption>
<tr><th>제목</th>
  <td style="text-align:left">${b.subject}</td></tr>
<tr><th>내용</th>
  <td><table style="width:100%; height:250px;"><tr>
  <td style="border-width:0px; vertical-align:top; text-align:left">
  ${b.content}</td></tr></table>
  </td></tr>
<tr><th>첨부파일</th>
<td>&nbsp;<c:if test="${!empty b.file1 }">
 <a href="file/${b.file1}">${b.file1}</a>
  </c:if></td></tr>
<tr><td colspan="2">
  <a href="updateForm.do?id=${b.id}&num=${b.num}&pageNum=${param.pageNum}&type=${b.type}">[수정]</a>
  <a href="deleteForm.do?id=${b.id}&num=${b.num}&pageNum=${param.pageNum}&type=${b.type}">[삭제]</a>
  <a href="list.do?pageNum=${param.pageNum}&id=${b.id}&type=${b.type}">[목록]</a>  
</td></tr></table></body></html>