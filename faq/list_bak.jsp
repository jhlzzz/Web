<%@page import="java.net.CookiePolicy"%>
<%@page import="java.net.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta charset="utf-8">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script>
<!DOCTYPE html>
<html>
<head>
	<title> FAQ </title>
	<meta charset="utf-8">
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
<center>
<font color='gray' size='4' face='Trebuchet MS'>
<hr width='600' size='2' color='gray' noshade>
<h3> FAQ </h3>
<font color='gray' size='4' face='Trebuchet MS'>
<div id="btn_write">
	<a href='write.do'>글쓰기</a><br/>
</div>
</font>
<hr width='600' size='2' color='gray' noshade>

<TABLE border='2' width='600' align='center' noshade>
<TR size='2' align='center' noshade bgcolor='skyblue'>
	<th bgcolor='skyblue'>number</th>
	<th color='gray'>subject</th>
	<th color='gray'>date</th>
	<th color='gray'>hits</th>
</TR>
<c:if test="${empty listResult}">
	<tr align="center" noshade>
	   <td colspan="5">데이터가 하나도 없음</td>
	</tr>
</c:if>
<c:forEach items="${listResult.list}" var="faq">
	<TR align='center' noshade>
		<TD>${faq.rnum}</TD>
	    <TD>
	      <a href="content.do?seq=${faq.seq}">
		    ${faq.subject}
		  </a>
		</TD>
		<TD>${faq.rdate}</TD>
		<TD>${faq.hits}</TD>
	   </TR> 
</c:forEach>      
     
</TABLE>
<hr width='600' size='2' color='gray' noshade>
<font color='gray' size='3' face='Trebuchet MS'>
    (총페이지수 : ${listResult.totalPageCount})
    &nbsp;&nbsp;&nbsp;
    <c:forEach begin="1" end="${listResult.totalPageCount}" var="i">
        <a href="list.do?cp=${i}">
   			<c:choose>
   			    <c:when test="${i==listResult.cp}">
                	<strong>${i}</strong>
                </c:when>
                <c:otherwise>
                    ${i}
                </c:otherwise>
			</c:choose>
    	</a>&nbsp;
    </c:forEach>
    ( TOTAL : ${listResult.totalCount} )
    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       페이지 싸이즈 : 
    <select id="psId" name="ps" onchange="f(this)">
    	<c:choose>
    		<c:when test="${listResult.ps == 3}">
    		   <option value="3" selected>3</option>
		       <option value="5">5</option>
		       <option value="10">10</option>
    		</c:when>
    		<c:when test="${listResult.ps == 5}">
    		   <option value="3">3</option>
		       <option value="5" selected>5</option>
		       <option value="10">10</option>
    		</c:when>
    		<c:when test="${listResult.ps == 10}">
    		   <option value="3">3</option>
		       <option value="5">5</option>
		       <option value="10" selected>10</option>
    		</c:when>
    	</c:choose>
    </select>
    
    <script language="javascript">
    window.onload = function(e){
		//document.getElementById("btn_write").style.display = "none";
    	$.ui.autocomplete.prototype._renderItem = function (ul, item) {
		    item.label = item.label.replace(new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + $.ui.autocomplete.escapeRegex(this.term) + ")(?![^<>]*>)(?![^&;]+;)", "gi"), "<strong>$1</strong>");
		    return $("<li></li>")
		            .data("item.autocomplete", item)
		            .append("<a>" + item.label + "</a>")
		            .appendTo(ul);
		};
		
		
				
		//input 태그 id가 subject
	    //*******if 괄호 안이 서브젝트면 이거 실행, else 면 content 넣어서 실행 하려면 어떡해야하나??*******
		$( "#blank" ).autocomplete({
	        source : function( request, response ) {
	             $.ajax({
	                    type: 'post',
	                    url: "autoData.json",
	                    dataType: "json",
	                    //request.term = $("#autocomplete").val()
	                    data: { subject : $("#blank").val()
	                    		, search_key : $("#search_key").val()},
	                    		//faq/autoData.json?subject=내&search_key=subject
	                    //select * from BOARD where writer like %?%;
	                    success: function(data) {//DB에 쿼리가 쳐지고 나서 결과값이  컨트롤러에서 list란 변수에 담김. 그리고 autoData.json으로 호출한 여기로 돌아옴. 결론, data가 list로 갱신.
	                        //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
	                        response(
	                            $.map(data, function(item) {// $.map(1,function(2))은 1파라미터 값을 2라는 이름의 맵으로 변환 
	                            	//console.log("data=>");
	                            	//console.log(data);
	                            	//console.log("item=>");
	                            	//console.log(item);
	                            	
	                            	//alert(item.subject);
	                            	if($("#search_key").val() == "subject"){
										 return {
												label: item.subject
												//value: item.subject
										 }		                               
	                            		
	                            	}else{
										 return {
												label: item.content 
												//value: item.content
										 }		                               
	                            		
	                            	}
	                            })
	                        );
	                    }
	               });
	            },
	        //조회를 위한 최소글자수
	        minLength: 1,
	        select: function( event, ui ) {
	            // 만약 검색리스트에서 선택하였을때 선택한 데이터에 의한 이벤트발생   
            	//alert("선택된 아이템: " + ui.item.value);  
	        }
	    });
	
	}	
       function f(select){
           //var el = document.getElementById("psId");
           var ps = select.value;
           //alert("ps : " + ps);
           location.href="list.do?ps="+ps;
       }
       
    </script>
    <form action="search.do">
	<center>

<select id="search_key" name="search_key" fw-filter="" fw-label="" fw-msg="" >
<option value="subject">제목</option>
<option value="content">내용</option>
</select>

<input id="blank" name="blank" size="10" ></input><button type="submit">검색</button>
	</center>
	</form>
    
</font>
<hr width='600' size='2' color='gray' noshade>
</font>
</center>
</body>
</html>