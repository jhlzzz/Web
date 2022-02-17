package land.mvc.model;

import static land.mvc.model.LandSQL.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import land.mvc.domain.Landmark;


	class LandRepository {
		private DataSource ds;
		LandRepository(){
			try {
				Context initContext = new InitialContext();
				Context envContext = (Context)initContext.lookup("java:/comp/env");
				ds = (DataSource)envContext.lookup("jdbc/myoracle");
			}catch(NamingException ne) {
			}
		}
	
	ArrayList<Landmark> list(long max, long min){
		ArrayList<Landmark> list = new ArrayList<Landmark>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = LIST_ALL;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, min);
			pstmt.setLong(2, max);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int lnum = rs.getInt(2);
				String lname = rs.getString(3);
				String lintro = rs.getString(4);
				int star = rs.getInt(14);
				String lphoto = rs.getString(10);
				String loriphoto = rs.getString(11);
				
				list.add(new Landmark(lnum,lname,lintro,-1,null,null,null,null,lphoto,loriphoto,-1,null,star,-1,-1));
			}
			return list;
		}catch(SQLException se1) {
			System.out.println("#Landmark mvc() se1: " + se1);
			return null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se2) {
				System.out.println("#Land mvc() se2: "+se2);
			}
		}
	}
	
	void delete(int lnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = DELETE;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, lnum);
			pstmt.executeUpdate();
		}catch(SQLException se1) {
			System.out.println("se1 : "+se1);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se2) {
				System.out.println("se2: "+se2);
			}
		}
	}
	
	Landmark content(int lnum){
		Landmark landmark = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = CONTENT;
				try {
					System.out.println("lnum :"+lnum);
					con = ds.getConnection();
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, lnum);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						int lnum1 = rs.getInt(1);
						String lname = rs.getString(2);
						String lintro = rs.getString(3);
						String laddress = rs.getString(5);
						String lphone = rs.getString(6);
						String lphoto = rs.getString(9);
						String loriphoto = rs.getString(10);
						String review = rs.getString(12);
						int star = rs.getInt(13);
						int views = rs.getInt(14);
						int division = rs.getInt(15);
						landmark = new Landmark(lnum, lname, lintro, -1, laddress, lphone, null, null, lphoto, loriphoto, -1, review, star, views, division);
					}
				return landmark;
				
			}catch(SQLException se1) {
				System.out.println("se1: "+ se1);
				return null;
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se2) {
					System.out.println("se2: "+ se2);
				}
			}
		}
	
	long boardCount() {
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         con = ds.getConnection();
	         pstmt = con.prepareStatement(BOARD_SIZE);
	         rs = pstmt.executeQuery();
	         long size = 0;
	         while(rs.next()) {
	            size++;
	         }
	         return size;
	      }catch(SQLException se) {
	         return -1;
	      }finally {
	         closeAll(con, pstmt, rs);
	      }
	   }
	void closeAll(Connection con, PreparedStatement pstmt, ResultSet rs) {
	      try {
	         if(rs != null) rs.close();
	         if(pstmt != null) pstmt.close();
	         if(con != null) con.close();
	      }catch(SQLException se) {
	      }
	   }
}
