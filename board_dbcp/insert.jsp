<%@ page contentType="text/html;charset=utf-8" import="javax.sql.DataSource, java.sql.*"%>
	<jsp:useBean id="dbcp" class="hak.dbcp.DbcpBean" scope="application"/>

<%
	String writer = request.getParameter("writer");
	String email = request.getParameter("email");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	DataSource ds = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	String sql = "insert into board values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
	out.println("<script>");
	try{
		ds = dbcp.getDs();
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, writer);
		pstmt.setString(2, email);
		pstmt.setString(3, subject);
		pstmt.setString(4, content);
		int i = pstmt.executeUpdate();
		if(i>0){
			out.println("alert('입력성공 with JSP')");
		}else{
			out.println("alert('입력실패 with JSP')");
		}
	}catch(SQLException se){
		out.println("alert('입력실패 with JSP')");
	}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	out.println("location.href='list.jsp'");
	out.println("</script>");
%>