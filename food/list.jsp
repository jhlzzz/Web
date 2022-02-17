<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList, food.mvc.domain.Food,jeju.all.consts.BoardConst"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="../css/content.css" rel="stylesheet" />
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Rokkitt" rel="stylesheet"> 
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
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


<meta charset='utf-8'>
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
<center>
      <center>

         
         <form name="paging" action="food.do?m=list" method="post" style="margin-left:-90; margin-top: 130; margin-bottom:-50;">
         <input type="hidden" name="b" value="list" />
         <select id="option" name="ps" onChange="pageSubmit()" style="margin-left:103;">
            <option value="0" selected>=== 페이지 선택 ===</option>
            <option value="4">페이지 4개씩 보기</option>
            <option value="8">페이지 8개씩 보기</option>
            <option value="12">페이지 12개씩 보기</option>
            <option value="16">페이지 16개씩 보기</option>
            <option value="20">페이지 20개씩 보기</option>
         </select>
         </form>
      </center>
<script>
         function pageSubmit(){
            document.paging.submit();
         }
</script>

<c:if test="${empty list }">
	     <tr>
				<td colspan="5" style="text-align:center">FOOD가 하나도 없네요</td>
		</tr>
</c:if>
      <% 
         ArrayList<Long> cpList = (ArrayList<Long>)request.getAttribute("cpList");
         long ps = (Long)request.getAttribute("ps");
         long cp = (Long)request.getAttribute("cp");
         if(ps == -1) ps = BoardConst.PAGE;
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
      <hr style="margin-top:60;" width='1000' size='2' noshade>
      <div align="center" style="margin-bottom:-80;" >      
         <tr>
            <td colspan="3" align="center">
            	<a style="display:inline-block"></a>
               <a id="back" style="display:inline-block" href="food.do?m=list&cp=<%=cp-1%>&ps=<%=ps%>">◀이전</a>
                      |
                 <a id="page1" href="food.do?m=list&cp=<%=cpListTemp.get(0)%>&ps=<%=ps%>"><%=cpListTemp.get(0)%></a>
                 <a id="page2" style="display:inline-block" href="food.do?m=list&cp=<%=cpListTemp.get(1)%>&ps=<%=ps%>"><%=cpListTemp.get(1)%></a>
                 <a id="page3" style="display:inline-block" href="food.do?m=list&cp=<%=cpListTemp.get(2)%>&ps=<%=ps%>"><b><%=cpListTemp.get(2)%></b></a>
               <a id="page4" style="display:inline-block" href="food.do?m=list&cp=<%=cpListTemp.get(3)%>&ps=<%=ps%>"><%=cpListTemp.get(3)%></a>
               <a id="page5" style="display:inline-block" href="food.do?m=list&cp=<%=cpListTemp.get(4)%>&ps=<%=ps%>"><%=cpListTemp.get(4)%></a>
                  |
                 <a id="front" style="display:inline-block" href="food.do?m=list&cp=<%=cp+1%>&ps=<%=ps%>">이후▶</a>
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

<div></div>
<c:forEach items="${list}" var="food">

    <!-- <ul class="row" style="width: 1500px;" align="center">
		<div style="float: left; width: 33%;">
		&nbsp;&nbsp;&nbsp;&nbsp;
            <div class="img-box" ><img src="../photo/${food.fphoto}" width="400" height="400" alt=""></div>
            <a href='food.do?m=content&fnum=${food.fnum}'>${food.fname}<input type="submit" value="상세내용"></a>
            <div class="">${food.fintro}</div>
            <div class="">평점 : ${food.star}</div>
            		&nbsp;&nbsp;&nbsp;&nbsp;
            		
		</div>

    	</ul> -->
	 <section class="hero" style="margin-bottom:-150; margin-left:-30; float: left; width: 25% ">
         <div class="container">

          <div class="row">   
        
         <div class="col-lg-6 offset-lg-3">
 
         <div class="cardbox shadow-lg bg-white">

          
          </div><!--/ cardbox-item -->
           <div class="img-box" ><img src="../photo/${food.fphoto}" width="400" height="400" alt=""></div>
          <div class="cardbox-base" style = "border-radius: 1px; padding: 30px; width: 400px; height: 100px; min-height: 190px; overflow: auto;">
          <font size="6" color="black" face="나눔고딕 ExtraBold">
          <a href='food.do?m=content&fnum=${food.fnum}'>${food.fname}</a>
          
          </font>
         
         <div style="height: auto; width: 100%;  border-top:2px solid silver;">
            <div class="" style="margin-top:5">${food.fintro}</div>
            <div class="">평점 : ${food.star}</div>
               
         </div><!--/ cardbox -->

           </div><!--/ col-lg-6 -->   

          </div><!--/ row -->
          
         </div><!--/ container -->
        </section>
 </c:forEach>

</center>