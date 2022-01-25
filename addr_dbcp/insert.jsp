<%@ page contentType="text/html;charset=utf-8" import="javax.sql.DataSource, java.sql.*"%>
	<jsp:useBean id="dbcp" class="hak.dbcp.DbcpBean" scope="application"/>

<%
	//request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	System.out.println("name: "+ name + "addr: " + addr);//Tomcat 창에 한글 나오는지 확인 위해 추가함
	DataSource ds = null;
	Connection con=null;
	PreparedStatement pstmt=null;
	String sql = "insert into address values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";
	

	out.println("<script>");
	try{
		ds = dbcp.getDs();
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, addr);
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