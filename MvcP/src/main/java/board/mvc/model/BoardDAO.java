package board.mvc.model;

import static board.mvc.model.BoardSQL.DELETE;
import static board.mvc.model.BoardSQL.INSERT;
import static board.mvc.model.BoardSQL.LIST;
import static board.mvc.model.BoardSQL.LIST_CON;
import static board.mvc.model.BoardSQL.UPDATE_LIST;
import static board.mvc.model.BoardSQL.UPDATE_UPDATE;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mvc.domain.Board;

class BoardDAO {
	private DataSource ds;
	BoardDAO() {
      try {
         Context initContext = new InitialContext();
         Context envContext  = (Context)initContext.lookup("java:/comp/env");
         ds = (DataSource)envContext.lookup("jdbc/myoracle");
      }catch(NamingException ne) {
      }
   }
	ArrayList<Board> list(){
		ArrayList<Board> list = new ArrayList<Board>();
		Connection con= null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = LIST;
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
				list.add(new Board(seq, writer, email, subject, content, rdate));
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
	boolean insert(Board dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = INSERT;
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
	ArrayList<Board> list_con(int seq) {
		ArrayList<Board> list = new ArrayList<Board>();
		Connection con= null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = LIST_CON+seq;
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
				list.add(new Board(seq, writer, email, subject, content, rdate));
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
	ArrayList<Board> update_list(int seq) {
		ArrayList<Board> list = new ArrayList<Board>();
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = UPDATE_LIST;
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
				Date rdate = rs.getDate(6);
				list.add(new Board(seq, writer, email, subject, content, rdate));
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
	void update_update(Board dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = UPDATE_UPDATE;
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
		//return �����ȰŴ�~ ���⼭ ���� ������Ʈ_������Ʈ�� ȣ���� ������ ����	
	}
	void delete(int seq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = DELETE;
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
	//return �����ȰŴ�~ ���⼭ ���� ����Ʈ�� ȣ���� ������ ����.
	}
}
	
