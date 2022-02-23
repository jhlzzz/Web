<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>list.jsp</title>
		<style>
			a{text-decoration:none}
		    table{
		        width:50%
		    }
			table,th,td {
			  border : 1px solid black;
			  border-collapse: collapse;
			}
			th,td {
			  padding: 5px;
			}
		</style>
	</head>
	<body style="text-align:center">
	    <h1>FileUpload with Spring5</h1>
	    <a href="form.do">업로드 폼</a>
	    &nbsp;&nbsp;&nbsp;&nbsp;
	    <a href="form_mt.do">업로드 폼(멀티)</a>
	    &nbsp;&nbsp;&nbsp;&nbsp;
	    <a href="../">인덱스</a>
	    <br/><br/>
	    
	    <center>
	    <table>
	        <tr>
	           <th>이름</th>
	           <th>크기</th>
	           <th>삭제</th>
	        </tr>
				<c:if test="${empty files}">
	           <tr>
	              <td align="center" colspan="3">파일이 하나도 없음</td>
	           </tr>
				</c:if>
				
				<c:forEach items="${files}" var="f">
	           <tr>
	             <td align="center">
	               <a href="download.do?fname=${f.name}">${f.name}</a>
	             </td>
	             <td align="center">${f.length()}</td>
	             <td align="center">
	               <a href="del.do?fname=${f.name}">삭제</a>
	             </td>
	          </tr>
				</c:forEach>
	    </table>
	    </center>
	</body>
</html>
