package hak.mv.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.sql.*;

public class AddrDAO {
   private DataSource ds;
   public AddrDAO() {
      try {
         Context initContext = new InitialContext();
         Context envContext  = (Context)initContext.lookup("java:/comp/env");
         ds = (DataSource)envContext.lookup("jdbc/myoracle");
      }catch(NamingException ne) {
      }
   }
   public ArrayList<AddrDTO> list(){
      ArrayList<AddrDTO> list = new ArrayList<AddrDTO>();
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;
      String sql = "select * from address order by seq desc";
      try{
         con = ds.getConnection();
         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);
         while(rs.next()){
            int seq = rs.getInt(1);
            String name = rs.getString(2);
            String addr = rs.getString(3);
            Date rdate = rs.getDate(4);
            list.add(new AddrDTO(seq, name, addr, rdate));
          }
         return list;
      }catch(SQLException se){
         System.out.println("#AddrDAO list() se: " + se);
         return null;
      }finally{
         try{
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(con != null) con.close();
         }catch(SQLException se){}
      }
   }
   public boolean insert(AddrDTO dto) { //구현
      Connection con = null;
      PreparedStatement pstmt = null;
      String sql = "insert into address values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";
      try{
         con = ds.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, dto.getName());
         pstmt.setString(2, dto.getAddr());
         int i = pstmt.executeUpdate();
         if(i>0){
            return true;
         }else{
            return false;
         }
      }catch(SQLException se){
         System.out.println("#AddrDAO insert() se: " + se);
         return false;
      }finally{
         try{
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
         }catch(SQLException se){}
      }
   }
   public void delete(int seq) { //구현
      Connection con = null;
      PreparedStatement pstmt = null;
      String sql = "delete from ADDRESS where SEQ=?";
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
   }
}