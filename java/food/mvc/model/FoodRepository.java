package food.mvc.model;


import static food.mvc.model.FoodSQL.*;

import java.sql.*;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import food.mvc.domain.Food;

class FoodRepository {
	private DataSource ds;
	FoodRepository(){
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	
	ArrayList<Food> list(long max, long min){
		ArrayList<Food> list = new ArrayList<Food>();
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
				int fnum = rs.getInt(2);
				String fname = rs.getString(3);
				String fintro = rs.getString(4);
				int star = rs.getInt(15);
				String fphoto = rs.getString(11);
				String foriphoto = rs.getString(12);
				
				list.add(new Food(fnum,fname,fintro,-1,null,null,null,null,null,fphoto,foriphoto,-1,null,star,-1,-1));
			}
			return list;
		}catch(SQLException se1) {
			System.out.println("#FOOD MVC() se: " + se1);
			return null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se2) {
			System.out.println("#food MVC() se: " + se2);	
			}
		}
	}
	
	void delete(int fnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = DELETE;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fnum);
			pstmt.executeUpdate();
		}catch(SQLException se1) {
			System.out.println("se1: "+se1);
		}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se2) {
					System.out.println("se2: "+se2);
				}
			}
		}
	Food content(int fnum){
		Food food = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = CONTENT;
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, fnum);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						int fnum1 = rs.getInt(1);
						String fname = rs.getString(2);
						String fintro = rs.getString(3);
						String faddress = rs.getString(5);
						String fphone = rs.getString(6);
						String fphoto = rs.getString(10);
						String foriphoto = rs.getString(11);
						String review = rs.getString(13);
						int star = rs.getInt(14);
						int views = rs.getInt(15);
						int division = rs.getInt(16);
						
						food = new Food(fnum, fname, fintro, -1, faddress, fphone, null, null, null, fphoto, foriphoto, -1, review, star, views, division);
					}
				return food;
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
