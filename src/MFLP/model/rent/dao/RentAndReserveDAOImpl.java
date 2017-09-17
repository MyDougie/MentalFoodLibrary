package MFLP.model.rent.dao;

import java.awt.image.DataBuffer;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MFLP.model.dto.MemberDTO;
import MFLP.model.dto.RentDTO;
import MFLP.model.dto.ReserveDTO;
import MFLP.model.util.DbUtil;

public class RentAndReserveDAOImpl implements RentAndReserveDAO {

   @Override
   public int rent(String memberId, int bookNo, boolean flag) throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      Statement stmt = null;
      String sql = "insert into rent values(rent_no.nextVal, ?, ?, sysdate, sysdate+14, null, 0)";
      int re1, re2;
   
      try{
         con = DbUtil.getConnection();   
         ps = con.prepareStatement(sql);
         ps.setString(1, memberId);
         ps.setInt(2, bookNo);
         re1 = ps.executeUpdate();//예약테이블에 삽입성공
         
         if(flag){//예약자가 없는 경우
            return (re1 == 1 && rentTrigger(memberId, bookNo) == 1) ? 1 : 0;
         }else{//대여자가 예약자인 경우 - reserve테이블에서 delete해줄 것.
            sql = "delete from reserve where member_Id = ? and book_No = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, memberId);
            ps.setInt(2, bookNo);
            re2 = ps.executeUpdate();//reserve테이블에서 삭제 성공?
            if(re1==1 && re2==1){
               return (re1 == 1 && rentTrigger(memberId, bookNo) == 1) ? 1 : 0;
            }
         }
         
      }finally{
         DbUtil.dbClose(con, ps, null);
      }
      
      return 0;
   }

   @Override
   public int returns(String memberId, int bookNo) throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      String sql = "update book set available = 0 where book_No = ?";
      int re1, re2, re3;
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setInt(1, bookNo);
         re1 = ps.executeUpdate();
         
         sql = "update member set rent_count = rent_count - 1 where member_id = ?";
         ps = con.prepareStatement(sql);
         ps.setString(1, memberId);
         re2 = ps.executeUpdate();
         
         sql = "update rent set return_date = sysdate where return_date is null and book_no = ?";
         ps = con.prepareStatement(sql);
         ps.setInt(1, bookNo);
         re3 = ps.executeUpdate();
         
         if(re1==1 && re2==1 && re3==1){
            return re1;
         }
         
      }finally{
         DbUtil.dbClose(con, ps, null);
      }
      
      return 0;
   }

   @Override
   public int reserve(String memberId, int bookNo, int rentNo) throws SQLException {
         // 책
         Connection con = null;
         PreparedStatement ps = null;
         String sql = "insert into reserve values(reserve_no.nextVal, ?, ?, ?, to_char(sysdate, 'YY-MM-DD HH:MI:SS'))";
         try {
            con = DbUtil.getConnection(); 
            ps = con.prepareStatement(sql);
            ps.setInt(1, rentNo);
            ps.setString(2, memberId);
            ps.setInt(3, bookNo);
            return ps.executeUpdate();

         } finally {
            DbUtil.dbClose(con, ps, null);
         }
         
      }


   @Override
   public int cancleReserve(int reserveNo, String memberId) throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      String sql = "delete from reserve where reserve_No = ?";
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setInt(1, reserveNo);
         
         return ps.executeUpdate();
         
      }finally{
         DbUtil.dbClose(con, ps, null);
      }
   }

   @Override
   public List<ReserveDTO> selectMyReserve(String memberId) throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<ReserveDTO> list = new ArrayList<>();
      String sql = "select reserve_no, title, member_id, reserve_date, rnum from(("
               +"select reserve_no, member_id, book_no, reserve_date, row_number() "
               + "over(partition by book_no order by book_no) rnum "
               + "from reserve order by book_no , reserve_date) natural join book) "
               + "where member_id = ?";
      
      //예약번호, 도서명, 예약자 ID, 예약일, 예약순위 
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setString(1, memberId);
         rs = ps.executeQuery();
         while(rs.next()){
            list.add(new ReserveDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
         }
         
         return list;
         
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
   }

   @Override
   public int getReservedCount(int bookNo) throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "select count(book_no) from reserve where book_no = ?";
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setInt(1, bookNo);
         rs = ps.executeQuery();
         if(rs.next()){
            return rs.getInt(1);
         }
         
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
      
      return 0;
   }

   @Override
   public boolean isAvailable(int bookNo) throws SQLException {
      Connection con = null;
      PreparedStatement ps =null;
      ResultSet rs = null;
      String sql ="select available from book where book_no = ?";
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setInt(1, bookNo);
         rs = ps.executeQuery();
         if(rs.next()){
            if(rs.getInt(1) == 0)
               return true;
         }
      
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
      return false;
      
   }

   @Override
   public int getRentCount(String memberId) throws SQLException {
      Connection con = null;
      PreparedStatement ps =null;
      ResultSet rs = null;
      String sql = "select rent_count from member where member_id = ?";
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setString(1, memberId);
         rs = ps.executeQuery();
         if(rs.next()){
            return rs.getInt(1);
         }
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
      
      return 0;
   }
   
   
   public boolean isFirstReserver(String memberId, int bookNo) throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "select * from(select member_id, rownum from reserve where book_no = ? order by reserve_date)"
            + " where rownum=1";
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setInt(1, bookNo);
         rs = ps.executeQuery();
         if(rs.next()){
            if(rs.getString(1).equals(memberId))
               return true;
         }
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
      
      return false;
      
   }

   @Override
   public int rentTrigger(String memberId, int bookNo) throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      String sql1 = "update member set rent_count = rent_count + 1 where member_id = ?";
      String sql2 = "update book set available = 1 where book_no = ?";
      int re1, re2;
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql1);
         ps.setString(1, memberId);
         re1 = ps.executeUpdate();
         
         ps = con.prepareStatement(sql2);
         ps.setInt(1, bookNo);
         re2 = ps.executeUpdate();
         
         if(re1==1 && re2==1)
            return re1;
         
      }finally{
         DbUtil.dbClose(con, ps, null);
      }
      
      return 0;
   }

   @Override
   public List<RentDTO> selectMyRent(String memberId) throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "select rent.book_no, title, start_date, end_date, extension_count, 100*trunc(sysdate-To_Date(end_date)+1), member_id "
            + "from rent join book on rent.book_no=book.book_no where member_id = ? and return_date is null";
      List<RentDTO> list = new ArrayList<>();
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setString(1, memberId);
         rs = ps.executeQuery();
         while(rs.next()){
            list.add(new RentDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
         }
         
         return list;
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
      
   }

   @Override
   public List<RentDTO> selectAllRent() throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "select r.member_id, r.book_no, b.title, start_date, end_date, extension_count, 100*trunc(sysdate-To_Date(end_date)+1)"
            + " from rent r join book b on r.book_no=b.book_no"
            + " where return_date is null";

      List<RentDTO> list = new ArrayList<>();
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         while(rs.next()){
            list.add(new RentDTO(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7)));
         }
         
         return list;
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
   }
   
   public int deleteFirstReserve(int bookNo) throws SQLException{
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      //해당 도서의 첫번째 예약자의 예약번호를 가져옴.
      String sql = "select * from(select reserve_no from reserve where book_no = ? order by reserve_date) "
            + "where rownum=1;";
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setInt(1, bookNo);
         rs = ps.executeQuery();
         
         if(rs.next()){
            int reserveNo = rs.getInt(1);
            
            //예약번호로 delete
            sql = "delete from reserve where reserve_no = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, reserveNo);
            
            return ps.executeUpdate();
         }
         
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
      
      return 0;
   }

   @Override
   public int getRentNo(int bookNo) throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "select rent_no from rent where book_no = ? and return_date is null";
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setInt(1, bookNo);
         rs = ps.executeQuery();
         if(rs.next()){
            return rs.getInt(1);
         }
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
      
      return 0;
   }

   @Override
   public List<ReserveDTO> selectAllReserve() throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<ReserveDTO> list = new ArrayList<>();
      String sql = "select reserve_no, title, member_id, reserve_date, rnum from(("
            + "select reserve_no, member_id, book_no, reserve_date, row_number() over(partition by book_no order by book_no) rnum"
            + " from reserve order by book_no, reserve_no) natural join book)";
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         while(rs.next()){
            list.add(new ReserveDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
         }
         return list;
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
   }

   @Override
   public int extent(int rentNo) throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      String sql = "update rent set end_date = To_Date(end_date, 'YY-MM-DD') + 7 where rent_no = ?";
      int re1, re2;
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setInt(1, rentNo);
         re1 = ps.executeUpdate();
         
         sql = "update rent set extension_Count = extension_count+1 where rent_no = ?";
         ps = con.prepareStatement(sql);
         ps.setInt(1, rentNo);
         re2 = ps.executeUpdate();
         
         if(re1==1 && re2==1)
            return re1;
         
      }finally{
         DbUtil.dbClose(con, ps, null);
      }
      return 0;
   }

   @Override
   public int getExtensionCount(int rentNo) throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "select extension_count from rent where rent_no = ?";
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setInt(1, rentNo);
         rs = ps.executeQuery();
         if(rs.next())
            return rs.getInt(1);
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
      return 0;
   }

   public boolean hasReserve(String memberId, int bookNo) throws Exception {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "select * from reserve where member_id = ? and book_no = ?";
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setString(1, memberId);
         ps.setInt(2, bookNo);
         rs = ps.executeQuery();
         if(rs.next())
            return true;
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
      return false;
   }

   @Override
   public List<ReserveDTO> selectSearchReserve(String keyField, String keyWord) throws Exception {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "select reserve_no, title, member_id, reserve_date, rnum from(("
            + "select reserve_no, member_id, book_no, reserve_date, row_number() over(partition by book_no order by book_no) rnum"
            + " from reserve order by book_no, reserve_no) natural join book) where ";

      List<ReserveDTO> list = new ArrayList<>();
      
      if(keyField.equals("title")){
         sql += "title like ?";
      }else if(keyField.equals("member_id")){
         sql += "member_id like ?";
      }
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setString(1, "%"+ keyWord + "%");
         rs = ps.executeQuery();
         while(rs.next()){
            list.add(new ReserveDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
         }
         
         return list;
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
      
   }

   public List<RentDTO> selectSearchRent(String keyField, String keyWord, int startRow, int endRow) throws Exception {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = null;
      
      if(keyField.equals("title")){
         sql = "select book_no, member_id, title, start_date, end_date, extension_count, 100*trunc(sysdate-To_Date(end_date)+1) from "
               + "(select rent.book_no, member_id, title, start_date, end_date, extension_count, 100*trunc(sysdate-To_Date(end_date)+1), rownum rnum"
               +" from rent join book on rent.book_no = book.book_no where title like ? and return_date is null)"
               +" where rnum between ? and ?";
         
      }else if(keyField.equals("member_id")){
         sql = "select book_no, member_id, title, start_date, end_date, extension_count, 100*trunc(sysdate-To_Date(end_date)+1) from "
               + "( select rent.book_no, member_id, title, start_date, end_date, extension_count, 100*trunc(sysdate-To_Date(end_date)+1), rownum rnum"
               +" from rent join book on rent.book_no = book.book_no where member_id like ? and return_date is null)"
               +" where rnum between ? and ?";
      }

      List<RentDTO> list = new ArrayList<>();
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setString(1, "%"+ keyWord + "%");
         ps.setInt(2, startRow);
         ps.setInt(3, endRow);
         rs = ps.executeQuery();
         while(rs.next()){
            list.add(new RentDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7)));
         }
         return list;
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
   }

   public int rentCount(String keyField, String keyWord) throws Exception{
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = null;
      
      if(keyField.equals("title")){
         sql = "select count(*)"
               + " from rent join book on rent.book_no = book.book_no where title like ? and return_date is null";
      }else if(keyField.equals("member_id")){
         sql = "select count(*)"
               + " from rent join book on rent.book_no = book.book_no where member_id like ? and return_date is null";
      }
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setString(1, "%"+ keyWord + "%");
         rs = ps.executeQuery();
         
         if(rs.next())
            return rs.getInt(1);
         
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
      
      
      return 0;
   }

   public boolean isYourRent(String memberId, int bookNo) throws Exception{
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "select member_id from rent where member_id = ? and book_no = ? and return_date is null";
      
      try{
         con = DbUtil.getConnection();
         ps = con.prepareStatement(sql);
         ps.setString(1, memberId);
         ps.setInt(2, bookNo);
         rs = ps.executeQuery();
         if(rs.next())
            return rs.getString(1).equals(memberId);
      }finally{
         DbUtil.dbClose(con, ps, rs);
      }
      return false;
   }
   
   
}