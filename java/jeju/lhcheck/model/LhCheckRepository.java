package jeju.lhcheck.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jeju.lhcheck.domain.LhCheck;
import static jeju.lhcheck.model.LhCheckSQL.*;

class LhCheckRepository {
	private DataSource ds;
	public LhCheckRepository() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
			System.out.println("NamingException: "+ ne);
		}
	}
	void lhInsert(LhCheck lhcheck) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(ONE_INSERT);
			pstmt.setInt(1, lhcheck.getClassfi());
			pstmt.setLong(2, lhcheck.getContentnum());
			pstmt.setString(3, lhcheck.getNick());
			pstmt.executeUpdate();
		}catch(SQLException se) {
		}finally {
			closeAll(con, pstmt, null);
		}
	}
	int loveCheck(LhCheck lhcheck) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int love = -1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ONE_LOVE_CHECK);
			pstmt.setInt(1, lhcheck.getClassfi());
			pstmt.setLong(2, lhcheck.getContentnum());
			pstmt.setString(3, lhcheck.getNick());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				love = rs.getInt(0);
			}
			return love;
		}catch(SQLException se) {
			System.out.println("check se: " + se);
			return love;
		}finally {
			closeAll(con, pstmt, rs);
		}
	}
	int hateCheck(LhCheck lhcheck) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int hate = -1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ONE_HATE_CHECK);
			pstmt.setInt(1, lhcheck.getClassfi());
			pstmt.setLong(2, lhcheck.getContentnum());
			pstmt.setString(3, lhcheck.getNick());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hate = rs.getInt(0);
			}
			return hate;
		}catch(SQLException se) {
			System.out.println("se: " + se);
			return hate;
		}finally {
			closeAll(con, pstmt, rs);
		}
	}
	void loveUpdate(LhCheck lhcheck) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ONE_LOVE_UPDATE);
			pstmt.setInt(1, lhcheck.getLove());
			pstmt.setInt(2, lhcheck.getClassfi());
			pstmt.setLong(3, lhcheck.getContentnum());
			pstmt.setString(4, lhcheck.getNick());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("update se: "+se);
		}finally {
			closeAll(con, pstmt, null);
		}
	}
	void hateUpdate(LhCheck lhcheck) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ONE_HATE_UPDATE);
			pstmt.setInt(1, lhcheck.getHate());
			pstmt.setInt(2, lhcheck.getClassfi());
			pstmt.setLong(3, lhcheck.getContentnum());
			pstmt.setString(4, lhcheck.getNick());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("update se: "+se);
		}finally {
			closeAll(con, pstmt, null);
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
