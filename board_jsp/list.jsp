<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<jsp:useBean id="pool" class="hak.db.ConnectionPoolBean" scope="application"/>

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
<hr width='600' size='2' noshade>
<h2>Simple Board with jsp, pool</h2>
&nbsp;&nbsp;&nbsp;
<a href='input.jsp'>글쓰기</a>
&nbsp;&nbsp;&nbsp;
<a href='../'>인덱스</a>
<hr width='600' size='2' noshade>
</center>
<table border='1' width='600' align='center' cellpadding='2'>
<tr>
<th align='center' width='10%'>글번호</th>
<th align='center' width='15%'>작성자</th>
<th align='center' width='30%'>이메일</th>
<th align='center' width='30%'>글제목</th>
<th align='center' width='15%'>날짜</th>
</tr>
<%
				Connection con= null;
				Statement stmt = null;
				ResultSet rs = null;
				String sql = "select * from board order by seq desc";
				try{
					con = pool.getConnection();
					stmt = con.createStatement();
					rs = stmt.executeQuery(sql);
					while(rs.next()){
						int seq = rs.getInt(1);
						String writer = rs.getString(2);
						String email = rs.getString(3);
						String subject = rs.getString(4);
						Date rdate = rs.getDate(6);
%>
					<tr>
						<td align='center'><%=seq%></td>
						<td><%=writer%></td>
						<td><%=email%></td>
						<td align='center'><a href='content.jsp?seq=<%=seq%>'><%=subject%></a>
						</td>
						<td align='center'><%=rdate%></td>
					</tr>
<%
					}
				}catch(SQLException se){
				}finally{
					try{
						if(rs != null) rs.close();
						if(stmt != null) stmt.close();
						if(con != null) pool.returnConnection(con);
					}catch(SQLException se){}
				}
%> 
	</table>
<hr width='600' size='2' noshade>