<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList, food.mvc.domain.Food"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	
	
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
		<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
		<link href="../css/styles.css" rel="stylesheet" />
		
		 <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav" style="background-color:black; opacity: 0.5;">
		    <div class="container">
		        <a class="navbar-brand" href="food.do?m=index"><img style="width:110; height:50;" src="../img/jeju2.png" alt="..." /></a>
		        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
		            Menu
		            <i class="fas fa-bars ms-1"></i>
		        </button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
		    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0" id="menuList">
            	<li class="nav-item" id="boardP"><a id="board" class="nav-link" href="../member/mController?message=loginform">게시판</a></li>
                <li class="nav-item" id="courseP"><a id="course" class="nav-link" href="../member/mController?message=loginform">코스추천</a></li>
                <li class="nav-item" id="contentsP"><a id="contents" class="nav-link" href="../member/mController?message=loginform">컨텐츠</a></li>
                <li class="nav-item" id="loginInfo"><a id="login" class="nav-link" href="../member/mController?message=loginform">로그인</a></li>
                <li class="nav-item" id="signupLogout"><a id="signup" class="nav-link" href="../member/mController?message=signform">회원가입</a></li>
            </ul>
        </div>
    </div>
    <a style='display:none;' id="boardLogin" class="nav-link" href="../jeju_board/jeju_board.do?message=mainBoard">게시판</a>
    <a style='display:none;' id="courseLogin" class="nav-link" href="../course/course.do?message=list">코스추천</a>
    <a style='display:none;' id="contentsLogin" class="nav-link" href="../contents/contents.do?message=list">컨텐츠</a>
    <a style='display:none;' id="info" class="nav-link" href="../member/mController?message=myPage">${sessionScope.Member_Nick}님 어서오세요</a>
    <a style='display:none;' id="logout" class="nav-link" href="../member/mController?message=logout">로그아웃</a>
</nav>
<c:if test="${sessionScope.Member_Nick ne null}">
	<script language=javascript>
        	let loginInfo = document.getElementById('loginInfo');
        	let signupLogout = document.getElementById('signupLogout');
        	let boardP = document.getElementById('boardP');
        	let courseP = document.getElementById('courseP');
        	let contentsP = document.getElementById('contentsP');
        	let login = document.getElementById('login');
        	let signup = document.getElementById('signup');
        	let board = document.getElementById('board');
        	let course = document.getElementById('course');
        	let contents = document.getElementById('contents');
    		loginInfo.removeChild(login);
    		signupLogout.removeChild(signup);
    		boardP.removeChild(board);
    		courseP.removeChild(course);
    		contentsP.removeChild(contents);
    		
    		let info = document.getElementById('info');
        	let logout = document.getElementById('logout');
        	let boardLogin = document.getElementById('boardLogin');
        	let courseLogin = document.getElementById('courseLogin');
        	let contentsLogin = document.getElementById('contentsLogin');
        	loginInfo.appendChild(info);
        	signupLogout.appendChild(logout);
        	boardP.appendChild(boardLogin);
        	courseP.appendChild(courseLogin);
        	contentsP.appendChild(contentsLogin);
        	
        	info.style.display = 'block';
        	logout.style.display = 'block';
        	boardLogin.style.display = 'block';
        	courseLogin.style.display = 'block';
        	contentsLogin.style.display = 'block';
    </script>
</c:if>
		<link href="../css/content.css" rel="stylesheet" />
		<link href="https://fonts.googleapis.com/css?family=Rokkitt" rel="stylesheet"> 
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<!------ Include the above in your HEAD tag ---------->
		<link href="https://fonts.googleapis.com/css?family=Rokkitt" rel="stylesheet"> 
		<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

<meta charset='utf-8'>
      <style>
         table, th, td {
            border: 1px solid white;
            border-collapse: collapse;
         }
         th, td {
            padding: 3px;
         }
         a { text-decoration:none }
      </style>
      <center>
      <div style="margin-top:130"></div>
      <img src ="../photo/${food.fphoto}">
      <font size="6" color="black" face="나눔고딕 ExtraBold">
      	<div style= "margin-bottom:20;"></div>
      	<h1>${food.fname}</h1>
      </font>
      <div align ="center" style= "margin-bottom:25;" ></div>
      ${food.fintro}
      <hr width='600' size='2' noshade>
      <table border='1' width='600' align='center' cellpadding='3'>
<c:if test="${empty food }">
    <tr>
            <td colspan="2" style="text-align:center">데이터가 하나도 없네요</td>
    </tr>
</c:if>

      <tr>
         <td align='center'>주소</td>
         <td>${food.faddress}</td>
      </tr>
      <tr>
         <td align='center'>전화번호</td>
         <td>${food.fphone}</td>
      </tr>
      <tr>
         <td align='center'>리뷰</td>
         <td>${food.review}</td>
      </tr>
      <tr>
         <td align='center'>평점</td>
         <td>${food.star}</td>
      </tr>
      <tr>
         <td align='center'>조회수</td>
         <td>${food.views}</td>
      </tr>
                                 
      </table>
      <hr width='600' size='2' noshade>
      <hr width='600' size='2' noshade>
      <b>
      <a href='food.do'>뒤로가기</a>
      </b>
      <hr width='600' size='2' noshade>
</center>