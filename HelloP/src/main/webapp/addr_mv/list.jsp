<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList, hak.mv.model.AddrDTO"%>
	<jsp:useBean id="addrDAO" class="hak.mv.model.AddrDAO" scope="application"/>

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
	<h1>
		Address List MV
	</h1>
	<a href="../">인덱스</a>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="input.jsp">입력폼</a>
	<table border='1' cellpadding='7' cellspacing='2' width='50%'>
	    <tr>
		    <th>번호</th>
			<th>이름</th>
			<th>주소</th>
			<th>날짜</th>
			<th>삭제</th>
		</tr>
<%
	ArrayList<AddrDTO> list = addrDAO.list();
if(list != null){
	int size = list.size();
	if(size != 0){
		for(AddrDTO dto :list){
%>
		<tr>
			<td align='center'><%=dto.getSeq()%></td>
			<td><%=dto.getName()%></td>
			<td><%=dto.getAddr()%></td>
			<td><%=dto.getRdate()%></td>
			<td align='center'><a href='del.jsp?seq=<%=dto.getSeq()%>'>삭제</a></td>
		</tr>
<%			
		}
	}else{
%>
		<tr>
			<td colspan="5" style="text-align:center">데이터가 없습니다</td>
		</tr>
<%		
	}
}	
%> 
	</table>
</center>