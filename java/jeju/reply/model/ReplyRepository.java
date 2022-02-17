package jeju.reply.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jeju.reply.domin.Reply;

class ReplyRepository {
	private DataSource ds;
	public ReplyRepository(){
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
			System.out.println("NamingException: "+ ne);
		}
	}
	public ArrayList<Reply>listAll() {
		ArrayList<Reply> list = new ArrayList<Reply>();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		return list;
	}
}
