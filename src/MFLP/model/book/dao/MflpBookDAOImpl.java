package MFLP.model.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import MFLP.model.dto.BookDTO;
import MFLP.model.dto.populerBookDTO;
import MFLP.model.util.DbUtil;

public class MflpBookDAOImpl implements MflpBookDAO {

	@Override
	public List<BookDTO> selectAll() throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookDTO> list = new ArrayList<>();

		String sql = "select * from BOOK";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				BookDTO book = new BookDTO();
				book.setBookNo(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setDescription(rs.getString(3));
				book.setWriter(rs.getString(4));
				book.setWriteDate(rs.getString(5));
				book.setPublisher(rs.getString(6));
				book.setAvailable(rs.getInt(7));
				book.setBookImg(rs.getString(8));
				book.setCategoryNo(rs.getInt(9));
				list.add(book);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return list;

	}

	@Override
	public List<BookDTO> searchBySelect(String keyWord, String keyField) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookDTO> list = new ArrayList<>();
		String sql = "select * from book where ";
		if (keyField.equals("category")) {
			sql += "category like ?";
		} else if (keyField.equals("bookNo")) {
			sql += "book_no like ?";
		} else if (keyField.equals("title")) {
			sql += "title like ?";
		} else {
			sql += "category like ? or book_no like ? or title like ?";
		}

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(keyWord));
			if (keyField.equals("ALL")) {
				ps.setString(2, "%" + keyWord + "%");
				ps.setString(3, "%" + keyWord + "%");
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new BookDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;

	}

	@Override
	 public int bookInsert(BookDTO book) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        int result = 0;
        String sql = "insert into BOOK(BOOK_NO, TITLE, DESCRIPTION, "
              + "WRITER, WRITE_DATE, PUBLISHER,AVAILABLE, BOOK_IMG,"
              + "CATEGORY_NO ) values(?,?,?,?,?,?,0,?,?)";
        
                 
        
        try{
           con = DbUtil.getConnection();
           
           ps = con.prepareStatement("select max(book_no)+1 from book");
           rs = ps.executeQuery();
           if(rs.next()){
           	count = rs.getInt(1);
           }    
           
           ps = con.prepareStatement(sql);
           ps.setInt(1, count);
           ps.setString(2, book.getTitle());
           ps.setString(3, book.getDescription());
           ps.setString(4, book.getWriter());
           ps.setString(5, book.getWriteDate());
           ps.setString(6, book.getPublisher());
           ps.setString(7, book.getBookImg());
           ps.setInt(8, book.getCategoryNo());
           result = ps.executeUpdate();               
           
        }finally{
           DbUtil.dbClose(con, ps, null);
        }
        
        return result;
     }

	@Override
	public int bookUpdate(BookDTO book) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		int i = 0;
		String sql = "UPDATE book SET title=?, description=? , writer=?, write_date = ?, publisher =?, category_no = ? WHERE book_no = ?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getDescription());
			ps.setString(3, book.getWriter());
			ps.setString(4, book.getWriteDate());
			ps.setString(5, book.getPublisher());
			ps.setInt(6, book.getCategoryNo());
			ps.setInt(7, book.getBookNo());
			System.out.println("1"+i);
			i = ps.executeUpdate();
			System.out.println("2"+i);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally {
			DbUtil.dbClose(con, ps, null);
		}
		return i;
	}

	@Override
	public List<populerBookDTO> popularBookList() throws SQLException {
		String sql = "select cnt, book.BOOK_NO, title, book.PUBLISHER, book.WRITER from((select count(book_no) as cnt, book_no from rent group by book_no) b join book on b.book_no= book.book_no) order by cnt DESC";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<populerBookDTO> list = new ArrayList<>();

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				populerBookDTO book = new populerBookDTO();
				book.setCount(rs.getInt(1));
				book.setBookNo(rs.getInt(2));
				book.setTitle(rs.getString(3));
				book.setPublisher(rs.getString(4));
				book.setWriter(rs.getString(5));
				list.add(book);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return list;
	}

	@Override
	public int bookNum() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select LAST_NUMBER from user_sequences where SEQUENCE_NAME='BOOK_NO'";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = Integer.parseInt(rs.getString(1));
			}
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return result;
	}

	@Override
	public List<BookDTO> getBoardList(HashMap<String, Object> listOpt) throws SQLException {
		List<BookDTO> list = new ArrayList<BookDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String opt = (String) listOpt.get("opt"); // 검색옵션(제목, 내용, 글쓴이 등..)
		String condition = (String) listOpt.get("condition"); // 검색내용
		int start = (Integer) listOpt.get("start"); // 현재 페이지번호
		String sql = null;

		try {
			con = DbUtil.getConnection();

			if (opt == null) {
				sql = "select * from (select rownum rnum, book_no, title, description, writer, write_date, publisher, available, book_img, category_no FROM ( select * from book order by book_no)) where rnum>=? and rnum<=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, start);
				ps.setInt(2, start + 9);

			} else if (opt.equals("0")) {
				sql = "select * from (select rownum rnum, book_no, title, description, writer, write_date, publisher, available, book_img, category_no FROM ( select * from book where title like ? order by book_no)) where rnum>=? and rnum<=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "%" + condition + "%");
				ps.setInt(2, start);
				ps.setInt(3, start + 9);

			} else if (opt.equals("1")) {
				sql = "select * from (select rownum rnum, book_no, title, description, writer, write_date, publisher, available, book_img, category_no FROM ( select * from book where description like ? order by book_no)) where rnum>=? and rnum<=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "%" + condition + "%");
				ps.setInt(2, start);
				ps.setInt(3, start + 9);

			} else if (opt.equals("2")) {
				sql = "select * from (select rownum rnum, book_no, title, description, writer, write_date, publisher, available, book_img, category_no FROM ( select * from book where description like ? or title like ? order by book_no)) where rnum>=? and rnum<=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "%" + condition + "%");
				ps.setString(2, "%" + condition + "%");
				ps.setInt(3, start);
				ps.setInt(4, start + 9);

			} else if (opt.equals("3")) {
				sql = "select * from (select rownum rnum, book_no, title, description, writer, write_date, publisher, available, book_img, category_no FROM ( select * from book where writer like ? order by book_no)) where rnum>=? and rnum<=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "%" + condition + "%");
				ps.setInt(2, start);
				ps.setInt(3, start + 9);

			} else if (opt.equals("4")) {
				sql = "select * from (select rownum rnum, book_no, title, description, writer, write_date, publisher, available, book_img, category_no FROM ( select * from book where title like ? or description like ? or writer like ? order by book_no)) where rnum>=? and rnum<=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "%" + condition + "%");
				ps.setString(2, "%" + condition + "%");
				ps.setString(3, "%" + condition + "%");
				ps.setInt(4, start);
				ps.setInt(5, start + 9);
			}

			// 10개의 글을 한 화면에 보여주는(start번째 부터 start+9까지) 쿼리
			// desc : 내림차순, asc : 오름차순 ( 생략 가능 )

			rs = ps.executeQuery();

			while (rs.next()) {
				BookDTO book = new BookDTO();

				book.setBookNo(rs.getInt(2));
				book.setTitle(rs.getString(3));
				book.setDescription(rs.getString(4));
				book.setWriter(rs.getString(5));
				book.setWriteDate(rs.getString(6));
				book.setPublisher(rs.getString(7));
				book.setAvailable(rs.getInt(8));
				book.setBookImg(rs.getString(9));
				book.setCategoryNo(rs.getInt(10));

				list.add(book);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return list;
	}

	public int getBoardListCount(HashMap<String, Object> listOpt) throws SQLException {
		int result = 0;
		String opt = (String) listOpt.get("opt"); // 검색옵션(제목, 내용, 글쓴이 등..)
		String condition = (String) listOpt.get("condition"); // 검색내용
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DbUtil.getConnection();
			String sql = "";

			if (opt == null) // 전체글의 개수
			{
				sql = "select count(*) from book";
				ps = con.prepareStatement(sql);

			} else if (opt.equals("0")) // 제목으로 검색한 글의 개수
			{
				sql = "select count(*) from book where title like ?";
				ps = con.prepareStatement(sql.toString());
				ps.setString(1, '%' + condition + '%');

			} else if (opt.equals("1")) // 내용으로 검색한 글의 개수
			{
				sql = "select count(*) from book where description like ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, '%' + condition + '%');

			} else if (opt.equals("2")) // 제목+내용으로 검색한 글의 개수
			{
				sql = "select count(*) from book where title like ? or description like ?";

				ps = con.prepareStatement(sql);
				ps.setString(1, '%' + condition + '%');
				ps.setString(2, '%' + condition + '%');

			} else if (opt.equals("3")) // 글쓴이로 검색한 글의 개수
			{
				sql = ("select count(*) from book where writer like ?");
				ps = con.prepareStatement(sql);
				ps.setString(1, '%' + condition + '%');
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {

		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return result;
	} // end getBoardListCount

}
