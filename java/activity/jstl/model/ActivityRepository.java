package activity.jstl.model;

import static activity.jstl.model.ActivitySQL.LIST;
import static activity.jstl.model.ActivitySQL.LIST_CON;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mvc.domain.Activity;

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
	ArrayList<Activity> list(){
		ArrayList<Activity> list = new ArrayList<Activity>();
		Connection con= null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = LIST;
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int anum = rs.getInt(1);
				String aname = rs.getString(2);
				String aintro = rs.getString(3);
				int acost = rs.getInt(4);
				String aaddress = rs.getString(5);
				String aphone = rs.getString(6);
				String aopcl = rs.getString(7);
				String aclosed = rs.getString(8);
				String aphoto = rs.getString(9);
				String aoriphoto = rs.getString(10);
				int choosed = rs.getInt(11);
				String review = rs.getString(12);
				int star = rs.getInt(13);
				int views = rs.getInt(14);
				int division = rs.getInt(15);
				list.add(new Activity(anum, aname, aintro, acost, aaddress, aphone, aopcl, aclosed, aphoto, aoriphoto, choosed, review, star, views, division));
			}
			return list;
		}catch(SQLException se){
			System.out.println("#ActivityRepository list() se: " + se);
	        return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
	ArrayList<Activity> list_con(int seq) {
		ArrayList<Activity> list = new ArrayList<Activity>();
		Connection con= null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = LIST_CON+seq;
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int anum = rs.getInt(1);
				String aname = rs.getString(2);
				String aintro = rs.getString(3);
				int acost = rs.getInt(4);
				String aaddress = rs.getString(5);
				String aphone = rs.getString(6);
				String aopcl = rs.getString(7);
				String aclosed = rs.getString(8);
				String aphoto = rs.getString(9);
				String aoriphoto = rs.getString(10);
				int choosed = rs.getInt(11);
				String review = rs.getString(12);
				int star = rs.getInt(13);
				int views = rs.getInt(14);
				int division = rs.getInt(15);
				list.add(new Activity(anum, aname, aintro, acost, aaddress, aphone, aopcl, aclosed, aphoto, aoriphoto, choosed, review, star, views, division));
			}
			return list;
		}catch(SQLException se){
			System.out.println("#ActivityRepository list() se: " + se);
	        return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
}
