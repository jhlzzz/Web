<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<jsp:useBean id="pool" class="hak.db.ConnectionPoolBean" scope="application"/>

<%!
	Connection con;
	PreparedStatement pstmt;
	String sql = "insert into board values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
	public void jspInit(){ //DB연결 
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
		String usr = "servlet";
		String pwd = "java";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, usr, pwd);
			pstmt = con.prepareStatement(sql);
		}catch(ClassNotFoundException cnfe){
			System.out.println("#Oracle driver loading failed");
		}catch(SQLException se){
			System.out.println("#init() se: " + se);
		}
	}
	public void jspDestroy(){ //DB연결해제 
		 try{
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch(SQLException se){}
	}
%>
<%
	String writer = request.getParameter("writer");
	String email = request.getParameter("email");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	Connection con = null;
	PreparedStatement pstmt = null;
	String sql = "insert into board values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
	out.println("<script>");
	try{
		con = pool.getConnection();
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
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		}
	out.println("location.href='list.jsp'");
	out.println("</script>");
%>