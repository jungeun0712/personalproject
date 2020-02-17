<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- project/WebContent/decorator/decorator.jsp --%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title><decorator:title /></title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-light-blue.css">
<link rel="stylesheet" href="${path}/css/main.css">
<style>
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
</style>
<decorator:head />
</head>
<body class="w3-content" style="max-width:1200px">
<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
  <div class="w3-container w3-display-container w3-padding-16">
    <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
    <h3 class="w3-wide"><b>오늘의 일기</b></h3>
  </div>
  <div class="w3-card w3-round w3-white">
        <div class="w3-container">
         <h4 class="w3-center">My Profile</h4>
         <c:choose>
		<c:when test="${empty sessionScope.login}">
			<a href="${path}/model2/member/loginForm.do">로그인하세요</a>		
		</c:when>		
		<c:otherwise>
			${sessionScope.login}님 안녕하세요!&nbsp;&nbsp;<br>
			<a href="${path}/model2/member/meminfo.do?id=${sessionScope.login}">내 정보</a>
		</c:otherwise>		
		</c:choose>	
        </div>
  </div>
  
  <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
    <a onclick="myAccFunc()" href="javascript:void(0)" class="w3-button w3-block w3-white w3-left-align" id="myBtn">
      익명게시판 <i class="fa fa-caret-down"></i>
    </a>
    <div id="demoAcc" class="w3-bar-block w3-hide w3-padding-large w3-medium">
      <a href="${path}/model2/board/list3.do?type=3" class="w3-bar-item w3-button">고민게시판</a>
    </div>
     <a onclick="myAccFunc1()" href="javascript:void(0)" class="w3-button w3-block w3-white w3-left-align" id="myBtn">
      마이페이지<i class="fa fa-caret-down"></i>
    </a>
    <div id="demoAcc1" class="w3-bar-block w3-hide w3-padding-large w3-medium">
      <a href="${path}/model2/board/list.do?id=${sessionScope.login}&type=1" class="w3-bar-item w3-button">일기장</a>
      <a href="${path}/model2/board/list2.do?id=${sessionScope.login}&type=2" class="w3-bar-item w3-button">Q&A일기장</a>
      <a href="${path}/model2/board/cal.do" class="w3-bar-item w3-button">출석체크</a>
      <a href="${path}/model2/member/meminfo.do?id=${sessionScope.login}" class="w3-bar-item w3-button">회원수정</a>
      <a href="${path}/model2/member/list.do" class="w3-bar-item w3-button">회원관리</a>
      <a href="${path}/model2/board/list5.do" class="w3-bar-item w3-button">Q&A질문관리</a>
      <a href="${path}/model2/board/warnForm.do" class="w3-bar-item w3-button">신고관리</a>
    </div>
  </div>
</nav>

<!-- Top menu on small screens -->
<header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
  <div class="w3-bar-item w3-padding-24 w3-wide">LOGO</div>
  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding-24 w3-right" onclick="w3_open()"><i class="fa fa-bars"></i></a>
</header>
<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:250px">

  <!-- Push down content on small screens -->
  <div class="w3-hide-large" style="margin-top:83px"></div>

  
  <!-- Top header -->
  <header class="w3-container w3-padding-large w3-xlarge w3-white">
    <div class="w3-right">
		<c:choose>
		<c:when test="${empty sessionScope.login}">
			<a href="${path}/model2/member/main.do">메인으로</a>
			<a href="${path}/model2/member/joinForm.do">회원가입</a>
		</c:when>		
	<c:otherwise>
			${sessionScope.login}님이 로그인 하였습니다.&nbsp;&nbsp;
			<a href="${path}/model2/member/logout.do">로그아웃</a>
			<a href="${path}/model2/board/list3.do?type=3">메인으로</a>
		</c:otherwise>		
		</c:choose>
		</div>
    </div>
  </header>
  <div style="padding-top: 16px; padding-right: 16px; padding-left: 256px; padding-bottom: 16px;">
<decorator:body /> <!-- joinForm의 body부분이 들어온다. -->
</div>
<script>
// Accordion 
function myAccFunc() {
  var x = document.getElementById("demoAcc");
  if (x.className.indexOf("w3-show") == -1) {
    x.className += " w3-show";
  } else {
    x.className = x.className.replace(" w3-show", "");
  }
}
function myAccFunc1() {
	  var x = document.getElementById("demoAcc1");
	  if (x.className.indexOf("w3-show") == -1) {
	    x.className += " w3-show";
	  } else {
	    x.className = x.className.replace(" w3-show", "");
	  }
	}
// Click on the "Jeans" link on page load to open the accordion for demo purposes
document.getElementById("myBtn").click();


// Open and close sidebar
function w3_open() {
  document.getElementById("mySidebar").style.display = "block";
  document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
  document.getElementById("mySidebar").style.display = "none";
  document.getElementById("myOverlay").style.display = "none";
}
</script>

</body>
</html>