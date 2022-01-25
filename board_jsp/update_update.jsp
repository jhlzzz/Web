<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<jsp:useBean id="pool" class="hak.db.ConnectionPoolBean" scope="application"/>

<%
			int seq = -1;
			String seqStr = request.getParameter("seq");
			String emailStr = request.getParameter("email");
			String subjectStr = request.getParameter("subject");
			String contentStr = request.getParameter("content");
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = "update board set email=?, subject=?, content=? where seq=?";
			if(seqStr!=null){
				seqStr = seqStr.trim();
				seq = Integer.parseInt(seqStr);
			}
			try{
				con = pool.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(4, seq);
				pstmt.setString(1, emailStr);
				pstmt.setString(2, subjectStr);
				pstmt.setString(3, contentStr);
				pstmt.executeUpdate();
			}catch(SQLException se){
			}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		}
			response.sendRedirect("list.jsp");
%>