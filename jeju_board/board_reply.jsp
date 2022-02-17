<%@ page contentType = "text/html;charset=utf-8" import="jeju.board.model.BoardConst"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.user}">
	location.href='boardclient.do?b=index';
</c:if>
<html>
  <head>
    <title>간단한 게시판</title>
	<meta charset='utf-8'>
	<script language="javascript">
	   function check()
	   {
	       for(var i=0; i<document.input.elements.length; i++)
		   {
		      if(document.input.elements[i].value == "")
			  {
			     alert("모든 값을 입력 하셔야 합니다. ");
				 return false;
			  }
		   }
		   document.input.submit();
       }
	</script>
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
  <body onload="input.name.focus()">
    <center>
	   <hr width="600" size="2" noshade>
	      <h2>댓글</h2>
		  <a href='boardclient.do'>글목록 </a>
	   <hr width="600" size="2" noshade>
	</center>
	<form name="input" method="post" action="boardclient.do?b=insert&type=<%=BoardConst.MAIN%>">
		<input type="hidden"  name="id" value="${sessionScope.user.id}">
		<input type="hidden"  name="nickName" value="${sessionScope.user.nickname}">
	   <table border="1" width="600" align="center"  cellpadding="3" cellspacing="1">
		  <tr>
		     <td align="center">글내용</td>
			 <td align="center"><textarea name="postcontent" rows="5" cols="53"></textarea></td>
		  </tr>
		  <tr>
		     <td colspan="2" align="center">
			    <input type="button" value="전송" onclick="check()">
				<input type="reset" value="다시입력">
			 </td>
		  </tr>
	   </table>
	   <br>
	   <hr width="600" size="2" noshade>
	</form>
  </body>
</html>