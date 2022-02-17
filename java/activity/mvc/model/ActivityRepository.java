package activity.mvc.model;

import static activity.mvc.model.ActivitySQL.*;

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

import activity.mvc.domain.Activity;


class ActivityRepository {
	private DataSource ds;
	ActivityRepository(){
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	
	ArrayList<Activity> list(long max, long min){
		ArrayList<Activity> list = new ArrayList<Activity>();
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
				int anum = rs.getInt(2);
				String aname = rs.getString(3);
				String aintro = rs.getString(4);
				int star = rs.getInt(14);
				String aphoto = rs.getString(10);
				String aoriphoto = rs.getString(11);
				list.add(new Activity(anum,aname,aintro,-1,null,null,null,null,aphoto,aoriphoto,-1,null,star,-1,-1));
			}
			return list;
		}catch(SQLException se1) {
			System.out.println("#Activity mvc() se1: " + se1);
			return null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se2) {
				System.out.println("#Activity mvc() se2: "+se2);
			}
		}
	}
	
	void delete(int anum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = DELETE;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, anum);
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
	
	Activity content(int anum){
		Activity activity = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = CONTENT;
				try {
					System.out.println("anum :"+anum);
					con = ds.getConnection();
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, anum);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						int anum1 = rs.getInt(1);
						String aname = rs.getString(2);
						String aintro = rs.getString(3);
						String aaddress = rs.getString(5);
						String aphone = rs.getString(6);
						String aphoto = rs.getString(9);
						String aoriphoto = rs.getString(10);
						String review = rs.getString(12);
						int star = rs.getInt(13);
						int views = rs.getInt(14);
						int division = rs.getInt(15);
						activity = new Activity(anum, aname, aintro, -1, aaddress, aphone, null, null, aphoto, aoriphoto, -1, review, star, views, division);
					}
				return activity;
				
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
