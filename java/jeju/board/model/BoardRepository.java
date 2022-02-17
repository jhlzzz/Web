package jeju.board.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jeju.board.domain.Board;
import static jeju.board.model.BoardSQL.*;

class BoardRepository {
	private DataSource ds;
	public BoardRepository() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
			System.out.println("NamingException: "+ ne);
		}
	}
	ArrayList<Board> listAll(long max, long min){
		ArrayList<Board> listAll = new ArrayList<Board>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LIST_ALL);
			pstmt.setLong(1, min);
			pstmt.setLong(2, max);
			rs = pstmt.executeQuery();
			while(rs.next()) { // rownum로 출력할때는 1번에 rnum이 들어오게 되서 2번 부터 값을 받아야한다.
				long bnum = rs.getLong(2);
				String nick = rs.getString(3);
				String subject = rs.getString(4);
				String kategorie = rs.getString(5);
				String tag = rs.getString(6);
				String content = rs.getString(7);
				long love = rs.getLong(8);
				long hate = rs.getLong(9);
				long views = rs.getLong(10);
				String bPhoto = rs.getString(11);
				String bOriPhoto = rs.getString(12);
				Date bDate = rs.getDate(13);
				Date upDate = rs.getDate(14);
				int division = rs.getInt(15);
				listAll.add(new Board(bnum, nick, subject, kategorie, tag, content, love, hate, views, bPhoto, bOriPhoto, bDate, upDate, division));
			}
			return listAll;
		}catch(SQLException se) {
			System.out.println("se: " + se);
			return null;
		}finally {
			closeAll(con, pstmt, rs);
		}
	}
	Board content(long bnum) {
		Board contentList = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CONTENT);
			pstmt.setLong(1, bnum);
			rs = pstmt.executeQuery();
			while(rs.next()) { // rownum로 출력할때는 1번에 rnum이 들어오게 되서 2번 부터 값을 받아야한다.
				String nick = rs.getString(2);
				String subject = rs.getString(3);
				String kategorie = rs.getString(4);
				String tag = rs.getString(5);
				String content = rs.getString(6);
				long love = rs.getLong(7);
				long hate = rs.getLong(8);
				long views = rs.getLong(9);
				String bPhoto = rs.getString(10);
				String bOriPhoto = rs.getString(11);
				Date bDate = rs.getDate(12);
				Date upDate = rs.getDate(13);
				int division = rs.getInt(14);
				contentList = new Board(bnum, nick, subject, kategorie, tag, content, love, hate, views, bPhoto, bOriPhoto, bDate, upDate, division);
			}
			return contentList;
		}catch(SQLException se) {
			System.out.println("se: " + se);
			return null;
		}finally {
			closeAll(con, pstmt, rs);
		}
			
	}
	boolean insert(Board board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, board.getNick());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getKategorie());
			pstmt.setString(4, board.getTag());
			pstmt.setString(5, board.getContent());
			pstmt.setString(6, board.getBphoto());
			pstmt.setString(7, board.getBoriphoto());
			pstmt.setInt(8, board.getDivision());
			int i = pstmt.executeUpdate();
			if(i>0) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException se) {
			return false;
		}finally {
			closeAll(con, pstmt, null);
		}
	}
	void delete(long bnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, bnum);
			pstmt.executeUpdate();
		}catch(SQLException se) {
		}finally {
			closeAll(con, pstmt, null);
		}
	}
	void update(Board board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getKategorie());
			pstmt.setString(3, board.getTag());
			pstmt.setString(4, board.getContent());
			if(board.getBphoto() != null && board.getBoriphoto() != null) {
				pstmt.setString(5, board.getBphoto());
				pstmt.setString(6, board.getBoriphoto());
			}
			pstmt.setInt(7, board.getDivision());
			pstmt.setLong(8, board.getBnum());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("update se: "+se);
		}finally {
			closeAll(con, pstmt, null);
		}
	}
	long viewsCheck(long postNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(VIEWS_CHECK);
			pstmt.setLong(1, postNumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				long views = rs.getLong(1);
				return views;
			}else {
				return -1;
			}
		}catch(SQLException se) {
			return -1;
		}finally {
			closeAll(con, pstmt, rs);
		}
	}
	void viewsUpdate(long postNumber, long views) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(VIEWS_UP);
			pstmt.setLong(1, views);
			pstmt.setLong(2, postNumber);
			pstmt.executeUpdate();
		}catch(SQLException se) {
		}finally {
			closeAll(con, pstmt, null);
		}
	}
	long loveCheck(long bnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOVE_CHECK);
			pstmt.setLong(1, bnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				long love = rs.getLong(1);
				return love;
			}else {
				return -1;
			}
		}catch(SQLException se) {
			return -1;
		}finally {
			closeAll(con, pstmt, rs);
		}
	}
	void loveUpdate(long bnum, long love) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOVE_UP);
			pstmt.setLong(1, love);
			pstmt.setLong(2, bnum);
			pstmt.executeUpdate();
		}catch(SQLException se) {
		}finally {
			closeAll(con, pstmt, null);
		}
	}
	long hateCheck(long bnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(HATE_CHECK);
			pstmt.setLong(1, bnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				long hate = rs.getLong(1);
				return hate;
			}else {
				return -1;
			}
		}catch(SQLException se) {
			return -1;
		}finally {
			closeAll(con, pstmt, rs);
		}
	}
	void hateUpdate(long bnum, long hate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(HATE_UP);
			pstmt.setLong(1, hate);
			pstmt.setLong(2, bnum);
			System.out.println("hate: " + hate);
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("hate se: " + se);
		}finally {
			closeAll(con, pstmt, null);
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
