<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>이모티콘선택하기</title>
</head>
<body>
<form action="infocal.do" method="post" name="f" onsubmit="return info">
<input type="hidden" name="id" value="${param.id}">
<table><caption>오늘의 기분을 선택해주세요!</caption>
<tr><td><input type="radio" name="icon" value="1"><img width="200" height="200" src="../../img/smile.jpeg">
		<input type="radio" name="icon" value="2"><img width="200" height="200" src="../../img/sad.jpg">
		<input type="radio" name="icon" value="3"><img width="200" height="200" src="../../img/smile.jpeg"></td></tr>
<tr><td colspan="2"><a href="javascript:document.f.submit()">[게시물수정]</a></td></tr>
</table>
</form>
</body>
</html>