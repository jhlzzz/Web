<%@ page contentType="text/html; charset=UTF-8" import="java.util.ArrayList,jeju.all.consts.BoardConst"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--<c:if test="${empty sessionScope.user}">
	location.href='boardclient.do?b=index';
</c:if>-->
<html>
	<meta charset='utf-8'>
	<head>
		<!--<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
		 이 스크립트 사용하면 댓글, 카메라 아이콘이 안보임 이게 무슨 스크립트인지도 모르겠음-->
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
	</head>	
	<body>
			
		<div style="margin-top:150;"></div>
		<center>
			<hr width='1000' size='2' noshade>
			<h2>Board</h2>
				&nbsp;&nbsp;&nbsp;
			<a href='jeju_board.do?message=input&type='>글쓰기</a>
				&nbsp;&nbsp;&nbsp;
			<a href='jeju_board.do?message=index'>인덱스</a>
			<hr width='1000' size='2' noshade>
			
			<form name="paging" action="jeju_board.do?message=mainBoard" method="post" style="margin-left:-90; margin-bottom:-50;">
			<input type="hidden" name="b" value="list" />
			<select id="option" name="ps" onChange="pageSubmit()" style="margin-left:103;">
				<option value="0" selected>=== 페이지 선택 ===</option>
				<option value="5">페이지 5개씩 보기</option>
				<option value="10">페이지 10개씩 보기</option>
				<option value="15">페이지 15개씩 보기</option>
				<option value="20">페이지 20개씩 보기</option>
				<option value="25">페이지 25개씩 보기</option>
				<option value="30">페이지 30개씩 보기</option>
			</select>
			</form>
		</center>
		<script>
			function pageSubmit(){
				document.paging.submit();
			}
		</script>
		<c:if test="${empty listAll}">
	<tr>
		<td colspan="6" style="text-align:center">데이터가 하나도 없어요</td>
	</tr>
</c:if>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Rokkitt" rel="stylesheet"> 
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="../css/content.css" rel="stylesheet" />
	<!------ Include the above in your HEAD tag ---------->
<c:forEach items="${listAll}" var="board">
	
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
			    <a class="m-0" href="jeju_board.do?message=content&bnum=${board.bnum}">${board.subject}</a>
				<br/>
				<small><span><i class="icon ion-md-pin"></i>${board.nick}</span></small>
				<small><span><i class="icon ion-md-time"></i> 10 hours ago</span></small>
			   </div>
			  </div><!--/ media -->
			 </div><!--/ cardbox-heading -->
			  
			 <div class="cardbox-item">
			  <img class="img-fluid" src="../photo/${board.bphoto}" alt="" onerror="this.src='../img/tempLogo.jpg'" width="100%" height="100%">
			 </div><!--/ cardbox-item -->
			 <div class="cardbox-base">
			  <ul class="float-right">
			   <li><a href=""><i class="fa fa-comments"></i></a></li>
			   <li><a><em class="mr-5">12</em></a></li>
			   <li><a><i class="fa fa-share-alt"></i></a></li>
			   <li><a><em class="mr-3">${board.views}</em></a></li>
			  </ul>
			  <ul>
			   <li><a><i class="fa fa-thumbs-up"></i></a></li>
			   <li><a><i class="fa fa-thumbs-down"></i></a></li>
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
	</c:forEach>

		<% 
			ArrayList<Long> cpList = (ArrayList<Long>)request.getAttribute("cpList");
			long ps = (Long)request.getAttribute("ps");
			long cp = (Long)request.getAttribute("cp");
			if(ps == -1) ps = BoardConst.BOARD_PAGE;
			if(cp == -1) cp = 1;
			long size = (Long)request.getAttribute("size");
			long cpListSize = cpList.size();
			ArrayList<Long> cpListTemp = cpList;
			if(cpListTemp.size() < 5) {
				for(int c=cpListTemp.size(); c<=5; c++) {
					cpListTemp.add((long)-1);
				}
			}
			long maxCp = 1;
			long minCp = 1;
			for(long lo: cpList){
				if(lo>maxCp){
					maxCp = lo;
				}
				if(lo<minCp){
					minCp = lo;
				}
			}
		%>		
		<div></div>
		<hr style="margin-top:100;" width='1000' size='2' noshade>
		<div align="center" style="margin-bottom:100;">		
			<tr>
				<td colspan="3" align="center">
					<a id="back" style="display:inline-block" href="jeju_board.do?message=mainBoard&cp=<%=cp-1%>&ps=<%=ps%>">◀이전</a>
			          	|
			        <a id="page1" href="jeju_board.do?message=mainBoard&cp=<%=cpListTemp.get(0)%>&ps=<%=ps%>"><%=cpListTemp.get(0)%></a>
			        <a id="page2" style="display:inline-block" href="jeju_board.do?message=mainBoard&cp=<%=cpListTemp.get(1)%>&ps=<%=ps%>"><%=cpListTemp.get(1)%></a>
			        <a id="page3" style="display:inline-block" href="jeju_board.do?message=mainBoard&cp=<%=cpListTemp.get(2)%>&ps=<%=ps%>"><b><%=cpListTemp.get(2)%></b></a>
					<a id="page4" style="display:inline-block" href="jeju_board.do?message=mainBoard&cp=<%=cpListTemp.get(3)%>&ps=<%=ps%>"><%=cpListTemp.get(3)%></a>
					<a id="page5" style="display:inline-block" href="jeju_board.do?message=mainBoard&cp=<%=cpListTemp.get(4)%>&ps=<%=ps%>"><%=cpListTemp.get(4)%></a>
						|
			        <a id="front" style="display:inline-block" href="jeju_board.do?message=mainBoard&cp=<%=cp+1%>&ps=<%=ps%>">이후▶</a>
					&nbsp;&nbsp;&nbsp;
						<%=cp%>page/${maxPage}pages
					  </td>
					  <td colspan="2" align="center">
					    총 게시물 수 : <%=size%>
				</td>
			</tr>
		</div>
		
			<script language=javascript>
		       	let back = document.getElementById('back');
		       	let page1 = document.getElementById('page1');
		       	let page2 = document.getElementById('page2');
		       	let page3 = document.getElementById('page3');
		       	let page4 = document.getElementById('page4');
		       	let page5 = document.getElementById('page5');
		   		let front = document.getElementById('front');
		   		
				if(<%=cp%> < 2){
					back.style.display = 'none';
				}else if(<%=cp%> == ${maxPage} && <%=cp%> == <%=maxCp%>){
			   		front.style.display = 'none';
				}
				if(<%=cpListSize%> == 1){
					page2.style.display = 'none';
					page3.style.display = 'none';
					page4.style.display = 'none';
					page5.style.display = 'none';
					front.style.display = 'none';
				}else if(<%=cpListSize%> == 2){
					page3.style.display = 'none';
					page4.style.display = 'none';
					page5.style.display = 'none';
				}else if(<%=cpListSize%> == 3){
					page4.style.display = 'none';
					page5.style.display = 'none';
				}else if(<%=cpListSize%> == 4){
					page5.style.display = 'none';
				}
		    </script>
		
		</center>
	</body>