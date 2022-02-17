<%@ page contentType="text/html;charset=utf-8" import="jeju.board.domain.Board, jeju.all.consts.BoardConst, jeju.all.consts.ClassfiConst"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<meta charset='utf-8'>
	<head>
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
		<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
		<link href="../css/styles.css" rel="stylesheet" />
		
		 <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav" style="background-color:black; opacity: 0.5;">
		    <div class="container">
		        <a class="navbar-brand" href="jeju_board.do?message=index"><img style="width:110; height:50;" src="../img/jeju2.png" alt="..." /></a>
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
		    <a style='display:none;' id="boardLogin" class="nav-link" href="jeju_board.do?message=mainBoard">게시판</a>
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
		
	
		<link href="https://fonts.googleapis.com/css?family=Rokkitt" rel="stylesheet"> 
		<link href="../css/content.css" rel="stylesheet" />
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<!------ Include the above in your HEAD tag ---------->
		<link href="https://fonts.googleapis.com/css?family=Rokkitt" rel="stylesheet"> 
		<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	</head>		
	<body>
		<style>
			table, th, td {
			   border: 1px solid black;
			   border-collapse: collapse;
			}
			th, td {
			   padding: 5px;
			}
			a { text-decoration:none }
		</style>
			
		<div style="margin-top:150;"></div>
		<center>
			<hr width='1000' size='2' noshade>
			<h2>게시글</h2>
				&nbsp;&nbsp;&nbsp;
			<a href='jeju_board.do?message=input&type='>글쓰기</a>
				&nbsp;&nbsp;&nbsp;
			<a href='jeju_board.do?message=index'>인덱스</a>
			<hr width='1000' size='2' noshade>
		
		<hr width='600' size='2' noshade>
		<b>
		<a id="postUpdate" style="display:none;" href='jeju_board.do?message=updateList&bnum=${board.bnum}'>수정</a>
		<a id="postDelete" style="display:none; margin-left:15;" href='jeju_board.do?message=delete&bnum=${board.bnum}'>삭제</a>
		<a style="margin-left:15;" href='jeju_board.do?message=mainBoard'>목록</a>
		<a style="margin-left:15;" href='jeju_board.do?message=insertRe&bnum=${board.bnum}&type=<%=BoardConst.RE%>'>답글</a>
		</b>
		<hr width='600' size='2' noshade>
		</center>
		
		<c:choose>
			<c:when test="${sessionScope.Member_Nick eq board.nick}">
				<script>
					let postUpdate = document.getElementById('postUpdate');
			        postUpdate.style.display = 'inline-block';
			        let postDelete = document.getElementById('postDelete');
		        	postDelete.style.display = 'inline-block';
			    </script>
			</c:when>
			<c:when test="${sessionScope.Member_Grade eq 'admin'}">
				<script>
			        let postDelete = document.getElementById('postDelete');
		        	postDelete.style.display = 'inline-block';
			    </script>
			</c:when>
		</c:choose>
		
		
	    <!-- ==============================================
	    Hero
	    =============================================== -->
<c:if test="${empty board}">
	<tr>
		<td colspan="6" style="text-align:center">데이터가 하나도 없어요</td>
	</tr>
</c:if>
        <section class="hero" style="margin-bottom:-150; ">
         <div class="container">
          <div class="row">	
		  
		   <div class="col-lg-6 offset-lg-3">
			
			<div class="cardbox shadow-lg bg-white">
			 
			 <div class="cardbox-heading">
			  <!-- START dropdown-->
			  <div class="dropdown float-right">
			   <button class="btn btn-flat btn-flat-icon" type="button" data-toggle="dropdown" aria-expanded="false">
				<em class="fa fa-ellipsis-h"></em>
			   </button>
			   <div class="dropdown-menu dropdown-scale dropdown-menu-right" role="menu" style="position: absolute; transform: translate3d(-136px, 28px, 0px); top: 0px; left: 0px; will-change: transform;">
				<a class="dropdown-item" href="#">Hide post</a>
				<a class="dropdown-item" href="#">Stop following</a>
				<a class="dropdown-item" href="#">Report</a>
			   </div>
			  </div><!--/ dropdown -->
			  <div class="media m-0">
			   <div class="d-flex mr-3">
				<a href=""><img class="img-fluid rounded-circle" src="http://www.themashabrand.com/templates/bootsnipp/post/assets/img/users/4.jpg" alt="User"></a>
			   </div>
			   <div class="media-body">
			   <div>
				   <font size="4" color="black" face="나눔고딕 ExtraBold">
				   	 	<span>${board.subject}</span>
				    </font>
			    </div>
				<small><span><i class="icon ion-md-pin"></i>${board.nick}</span></small>
				<small><span><i class="icon ion-md-time"></i> 10 hours ago</span></small>
			   </div>
			  </div><!--/ media -->
			 </div><!--/ cardbox-heading -->
			  
			 <div class="cardbox-item">
			  <img class="img-fluid" src="../photo/${board.bphoto}" alt="" onerror="this.src='../img/tempLogo.jpg'" width="100%">
			 </div><!--/ cardbox-item -->
			 <div class="cardbox-base">
			  <ul class="float-right">
			   <li><a><i class="fa fa-comments"></i></a></li>
			   <li><a><em class="mr-5">12</em></a></li>
			   <li><a><i class="fa fa-share-alt"></i></a></li>
			   <li><a><em class="mr-3">${board.views}</em></a></li>
			  </ul>
			  <ul>
			   <li><a href="../LhCheck/LhCheck.do?message=loveCheck&contentnum=${board.bnum}&classFi=<%=ClassfiConst.BOARD%>&nick=${board.nick}"><i class="fa fa-thumbs-up"></i></a></li>
			   <li><a href="../LhCheck/LhCheck.do?message=hateCheck&contentnum=${board.bnum}&classFi=<%=ClassfiConst.BOARD%>&nick=${board.nick}"><i class="fa fa-thumbs-down"></i></a></li>
			   <li><a href="#"><img src="http://www.themashabrand.com/templates/bootsnipp/post/assets/img/users/3.jpeg" class="img-fluid rounded-circle" alt="User"></a></li>
			   <li><a href="#"><img src="http://www.themashabrand.com/templates/bootsnipp/post/assets/img/users/1.jpg" class="img-fluid rounded-circle" alt="User"></a></li>
			   <li><a href="#"><img src="http://www.themashabrand.com/templates/bootsnipp/post/assets/img/users/5.jpg" class="img-fluid rounded-circle" alt="User"></a></li>
			   <li><a href="#"><img src="http://www.themashabrand.com/templates/bootsnipp/post/assets/img/users/2.jpg" class="img-fluid rounded-circle" alt="User"></a></li>
			   <li><a><span>${board.love} Loves</span></a></li>
			   <li><a><span>${board.hate} Hates</span></a></li>
			  </ul>			   
			 </div><!--/ cardbox-base -->
			 <div class="cardbox-comments cardbox-item" style="margin-left:-25; margin-right:30;">
			  <span class="comment-avatar float-left">
			   <a href=""><img class="rounded-circle" src="http://www.themashabrand.com/templates/bootsnipp/post/assets/img/users/6.jpg" alt="..."></a>                            
			  </span>
			  <div class="search cardbox-item">
			   <input placeholder="Write a comment" type="text">
			   <button><i class="fa fa-camera"></i></button>
			  </div><!--/. Search -->
			 </div><!--/ cardbox-like -->			  
					
			</div><!--/ cardbox -->

           </div><!--/ col-lg-6 -->	

          </div><!--/ row -->
         </div><!--/ container -->
        </section>
		
		</center>
			
		

	</body>
</html>
