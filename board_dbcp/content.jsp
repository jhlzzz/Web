<%@ page contentType="text/html;charset=utf-8" import="javax.sql.DataSource, java.sql.*"%>
	<jsp:useBean id="dbcp" class="hak.dbcp.DbcpBean" scope="application"/>

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
<h2>Simple Board with dbcp</h2>
&nbsp;&nbsp;&nbsp;
<a href='input.jsp'>글쓰기</a>
<hr width='600' size='2' noshade>
<table border='1' width='600' align='center' cellpadding='3'>
<%
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "select * from board where seq=?";
		ResultSet rs = null;
		int seq = -1;
		String seqStr = request.getParameter("seq");
		if(seqStr!=null){
			seqStr = seqStr.trim();
			seq = Integer.parseInt(seqStr);
		}
		try{
			ds = dbcp.getDs();
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String writer = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
%>
			<tr>
			<td width='100' align='center'>글번호</td>
			<td><%=seq%></td>
			</tr>
			<tr>
			<td align='center'>글쓴이</td>
			<td><%=writer%></td>
			</tr>
			<tr>
			<td align='center'>이메일</td>
			<td><%=email%></td>
			</tr>
			<tr>
			<td align='center'>글제목</td>
			<td><%=subject%></td>
			</tr>
			<tr>
			<td align='center'>글내용</td>
			<td><%=content%></td>
			
<%
			}
		}catch(SQLException se){
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
%> 
</tr>
			</table>
			<hr width='600' size='2' noshade>

			<b>
			<a  href='update_list.jsp?seq=<%=seq%>'>수정</a>
			|
			<a href='del.jsp?seq=<%=seq%>'>삭제</a>
			|
			<a href='list.jsp'>목록</a>
			</b>
			<hr width='600' size='2' noshade>
			</center>