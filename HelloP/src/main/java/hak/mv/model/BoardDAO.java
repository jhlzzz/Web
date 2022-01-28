package hak.mv.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.sql.*;

public class BoardDAO {
	private DataSource ds;
	public BoardDAO() {
      try {
         Context initContext = new InitialContext();
         Context envContext  = (Context)initContext.lookup("java:/comp/env");
         ds = (DataSource)envContext.lookup("jdbc/myoracle");
      }catch(NamingException ne) {
      }
   }
	public ArrayList<BoardDTO> list(){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con= null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from board order by seq desc";
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int seq = rs.getInt(1);
				String writer = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
				Date rdate = rs.getDate(6);
				list.add(new BoardDTO(seq, writer, email, subject, content, rdate));
			}
			return list;
	}catch(SQLException se){
		System.out.println("#BoardDAO list() se: " + se);
        return null;
	}finally{
		try{
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(con != null) con.close();
		}catch(SQLException se){}
	}
}
	public boolean insert(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into board values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}else{
				return false;
			}
		}catch(SQLException se){
			System.out.println("#BoardDAO insert() se: " + se);
	        return false;
		}finally{
				try{
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se){}
			}
	}
	public ArrayList<BoardDTO> list_con(int seq) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con= null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from board where seq="+seq;
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String writer = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
				Date rdate = rs.getDate(6);
				list.add(new BoardDTO(seq, writer, email, subject, content, rdate));
			}
			return list;
		}catch(SQLException se){
			System.out.println("#BoardDAO list() se: " + se);
	        return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
	public ArrayList<BoardDTO> update_list(int seq) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "select * from board where seq=?";
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String writer = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
			}
			return list;
		}catch(SQLException se){
			System.out.println("#BoardDAO list() se: " + se);
	        return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}		
	
	public void update_update(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update board set email=?, subject=?, content=? where seq=?";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(4, dto.getSeq());
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.executeUpdate();
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
		//return 생략된거다~ 여기서 나인 업데이트_업데이트를 호출한 곳으로 간다	
	}
	
	public void delete(int seq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from board where seq=?";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	//return 생략된거다~ 여기서 나인 딜리트를 호출한 곳으로 간다.
	}
}
	
