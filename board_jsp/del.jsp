<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<jsp:useBean id="pool" class="hak.db.ConnectionPoolBean" scope="application"/>

<%!
private int getSeq(HttpServletRequest request){
        int seq = -1;
		String seqStr = request.getParameter("seq");
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr); 
					return seq;
				}catch(NumberFormatException ne){
				}
			}
		}
		return seq;
	}
%>
<%   
	int seq = getSeq(request);
	if(seq != -1){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from board where seq=?";
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		}
	}
	response.sendRedirect("list.jsp");
%>