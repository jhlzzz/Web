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
<h2>Simple Board with Servlet</h2>
&nbsp;&nbsp;&nbsp;
<a href='list.jsp'>글목록</a>
<hr width='600' size='2' noshade>
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
<form name='f' method='post' action='update_update.jsp'>
<input type='hidden' name='seq' value='<%=seq%>'>
<input type='hidden' name='writer' value='<%=writer%>'>
<table border='1' width='600' align='center' cellpadding='3' cellspacing='1'><tr>
<td width='30%' align='center'>글쓴이</td>
<td align='center'><input type='text' name='aa' size='60' value='<%=writer%>' disabled></td>
</tr>
<tr>
<td width='30%' align='center'>이메일</td>
<td align='center'><input type='text' name='email' size='60' value='<%=email%>'></td>
</tr>
<tr>
<td width='30%' align='center'>글제목</td>
<td align='center'><input type='text' name='subject' size='60' value='<%=subject%>'></td>
</tr>
<tr>
<td width='30%' align='center'>글내용</td>
<td align='center'><textarea name='content' rows='5' cols='53'><%=content%></textarea></td>
</tr>

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
<tr>
<td colspan='2' align='center'>
<input type='submit' value='수정'>
</td>
</tr>
</table>
</form>
</table>
</center>
