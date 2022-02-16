<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList, mvc.domain.Course"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<jsp:include page="../login/login_check_module.jsp"/>  --%>

<meta charset='utf-8'>
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
<link href="../css/styles.css" rel="stylesheet" />
 <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav" style="background-color:black; opacity: 0.5;">
    <div class="container">
        <a class="navbar-brand" href="../index.jsp"><img style="width:110; height:50;" src="../img/jeju2.png" alt="..." /></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars ms-1"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0" id="menuList">
                <li class="nav-item"><a class="nav-link" href="#services">게시판</a></li>
                <li class="nav-item"><a class="nav-link" href="./course.do">코스추천</a></li>
                <li class="nav-item" id="boardP"><a id="board" class="nav-link" href="login/login.do?m=loginmenu">마이페이지</a></li>
                <li class="nav-item" id="loginInfo"><a id="login" class="nav-link" href="login/login.do?m=loginmenu">Login</a></li>
                <li class="nav-item" id="signupLogout"><a id="signup" class="nav-link" href="login/login.do?m=signin">Sign Up</a></li>
            </ul>
        </div>
    </div>
    <a style='display:none;' id="info" class="nav-link" href="login/login.do?m=info">fdsfsd 님 어서오세요</a>
    <a style='display:none;' id="logout" class="nav-link" href="login/login.do?m=logout">logout</a>
    <a style='display:none;' id="boardLogin" class="nav-link" href="boardclient/boardclient.do?b=list">Board</a>
</nav>
<center>
<hr width='600' size='2' noshade>
</br></br></br></br>
<h4>원하시는 테마를 선택하세요</h4>

<form>
	<table border="1" width="800" height="200">
		<tr>			
				<img width="473" alt="제주도 사진" usemap="#jejumap" src="../img/${imageName}">
				<map name="jejumap">
				    <area shape="rect" coords="190,124,290,180" href='course.do?m=list&division=1' alt="제주의 숲">
				    <area shape="rect" coords="140,44,285,97" href='course.do?m=list&division=2' alt="제주의 화려한 밤">
				    <area shape="rect" coords="23,168,137,232" href='course.do?m=list&division=3' alt="제주에서의 힐링">
				    <area shape="rect" coords="168,204,300,262" href='course.do?m=list&division=4' alt="열대지방 제주">
				    <area shape="rect" coords="314,41,435,110" href='course.do?m=list&division=5' alt="모험가득한 제주">
				</map>
		</tr>
	</table>
</form>
<hr width='600' size='2' noshade>
</center>
<style>
table, th, td {
border: 1px solid white;
border-collapse: collapse;
}
th, td {
padding: 5px;
}
a { text-decoration:none }
</style>
<table border='1' width='600' align='center' cellpadding='3' style="margin-top:-200; margin-right:750">
<font size="100"; color="black"; face="나눔고딕 ExtraBold">
<tr>
		<td width='85%' align='center'><Strong>코스이름</Strong></td>
		<td width='15%' align='center'><Strong>좋아요</Strong></td>
</tr>
</font>
<c:if test="${empty list}">
	<tr>
		<td colspan="5" style="text-align:center">추천코스가 없습니다</td>
	</tr>
</c:if>
<c:forEach items="${list}" var="course">
	<tr>
		<td align='center'><a href='course.do?m=list_con&seq=${course.cnum}'>${course.cname}</a>
		</td>
		<td align='center'>${course.love}</td>
	</tr>
</c:forEach>
</table>
<hr width='600' size='2' noshade>