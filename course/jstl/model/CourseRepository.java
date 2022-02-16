package course.jstl.model;

import static course.jstl.model.CourseSQL.LIST1;
import static course.jstl.model.CourseSQL.LIST2;
import static course.jstl.model.CourseSQL.LIST3;
import static course.jstl.model.CourseSQL.LIST4;
import static course.jstl.model.CourseSQL.LIST5;
import static course.jstl.model.CourseSQL.LIST6;
import static course.jstl.model.CourseSQL.LIST_CON;

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

import mvc.domain.Course;

class CourseRepository {
	private DataSource ds;
	CourseRepository(){
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	ArrayList<Course> list(String dvs){
		ArrayList<Course> list = new ArrayList<Course>();
		Connection con= null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		System.out.println(dvs);
		switch(dvs) {
		case "-1" : sql = LIST6;break;
		case "1" : sql = LIST1; break;
		case "2" : sql = LIST2; break;
		case "3" : sql = LIST3; break;
		case "4" : sql = LIST4; break;
		case "5" : sql = LIST5; break;
		}
		
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				long cnum = rs.getLong(1);
				String cname = rs.getString(2);
				String cintro = rs.getString(3);
				String cnavi = rs.getString(4);
				long choosed = rs.getLong(5);
				long love = rs.getLong(6);
				long hate = rs.getLong(7);
				long mnum = rs.getLong(8);
				long anum = rs.getLong(9);
				long lnum = rs.getLong(10);
				long snum = rs.getLong(11);
				String cphoto = rs.getString(12);
				String coriphoto = rs.getString(13);
				int division = rs.getInt(14);
				list.add(new Course(cnum, cname, cintro, cnavi, choosed, love, hate, mnum, anum, lnum, snum, cphoto, coriphoto, division));
			}
			return list;
	}catch(SQLException se){
		System.out.println("#CourseRepository list() se: " + se);
        return null;
	}finally{
		try{
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(con != null) con.close();
		}catch(SQLException se){}
	}
}
	Course list_con(long seq) {
		Course course = null;
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = LIST_CON;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, seq);
			rs = pstmt.executeQuery();
			while(rs.next()){
				long cnum = rs.getLong(1);
				String cname = rs.getString(2);
				String cintro = rs.getString(3);
				String cnavi = rs.getString(4);
				long choosed = rs.getLong(5);
				long love = rs.getLong(6);
				long hate = rs.getLong(7);
				long mnum = rs.getLong(8);
				long anum = rs.getLong(9);
				long lnum = rs.getLong(10);
				long snum = rs.getLong(11);
				String cphoto = rs.getString(12);
				String coriphoto = rs.getString(13);
				int division = rs.getInt(14);
				course = new Course(cnum, cname, cintro, cnavi, choosed, love, hate, mnum, anum, lnum, snum, cphoto, coriphoto, division);
			}
			return course;
		}catch(SQLException se){
			System.out.println("#CourseRepository list() se: " + se);
	        return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
}
