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
	<title> 자주묻는질문 </title>
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
<font color='gray' size='4' face='휴먼편지체'>
<hr width='600' size='2' color='gray' noshade>
<h3> 검색결과</h3>
<hr width='600' size='2' color='gray' noshade>

<TABLE border='2' width='600' align='center' noshade>
<TR size='2' align='center' noshade bgcolor='AliceBlue'>
	<th bgcolor='AliceBlue'>no</th>
	<th color='gray'>subject</th>
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
	   </TR> 
</c:forEach>      
     
</TABLE>
<hr width='600' size='2' color='gray' noshade>
<font color='gray' size='3' face='휴먼편지체'>
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
	    $( "#subject" ).autocomplete({
	        source : function( request, response ) {
	             $.ajax({
	                    type: 'post',
	                    url: "autoData.json",
	                    dataType: "json",
	                    //request.term = $("#autocomplete").val()
	                    data: { subject : $("#subject").val()},
	                    //select * from BOARD where writer like %?%;
	                    success: function(data) {
	                        //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
	                        response(
	                            $.map(data, function(item) {
	                            	//alert(item.subject);
											 return {
													label: item.subject, 
													value: item.subject
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
           location.href="search.do?ps="+ps+"&search_key=${listResult.search_key}&blank=${listResult.blank}";
       }
       
    </script>
    
	<form action="search.do">
	<center>
		검색 <input id="subject" name="subject" size="10"></input><button type="submit">검색</button>
	</center>
	</form>
    
</font>
<hr width='600' size='2' color='gray' noshade>

</center>
</body>
</html>