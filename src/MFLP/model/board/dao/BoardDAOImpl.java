package MFLP.model.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MFLP.model.dto.BoardDTO;
import MFLP.model.util.DbUtil;

public class BoardDAOImpl implements BoardDAO {

	
	@Override
	public int insertBoard(BoardDTO boardDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "";
		// System.out.println("DAO에 들어온 DTO : " + boardDTO);
		if (boardDTO.getParentNo() == 0) {
			sql = "insert into board values(board_no.nextval, ?, ?, sysdate, ?, 0, ?, ?, null)";
		} else {
			sql = "insert into board values(board_no.nextval, ?, ?, sysdate, ?, 0, ?, ?, ?)";
		}
		// System.out.println("sql : " + sql);
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, boardDTO.getTitle());
			ps.setString(2, boardDTO.getContent());
			ps.setString(3, boardDTO.getFileName());
			ps.setInt(4, boardDTO.getCategory());
			ps.setString(5, boardDTO.getWriterId());
			if (boardDTO.getParentNo() != 0) {
				ps.setInt(6, boardDTO.getParentNo());
			}
			result = ps.executeUpdate();
			// System.out.println("결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(con, ps, null);
		}
		return result;
	}

	@Override
	public int deleteBoard(String id, int boardNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete from board where board_no=?and writer_id = ?";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNo);
			ps.setString(2, id);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(con, ps, null);
		}
		return result;
	}

	@Override
	public int updateBoard(BoardDTO boardDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "update board set title=?, content=?,file_name=?,readnum=? where board_no=? and writer_id=?";

		try {

			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, boardDTO.getTitle());
			ps.setString(2, boardDTO.getContent());
			ps.setString(3, boardDTO.getFileName());
			ps.setInt(4, boardDTO.getReadNum());
			ps.setInt(5, boardDTO.getBoardNo());
			ps.setString(6, boardDTO.getWriterId());

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(con, ps, null);
		}
		return result;
	}

	@Override
	public BoardDTO boardSearchByNo(int no, boolean flag) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		BoardDTO dto = null;
		String sql = "select * from board where board_no = ?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8),
						rs.getInt(9));
				if (flag) {
					int index = rs.getInt("readnum");
					index++;
					dto.setReadNum(index);
					// System.out.println(temp.toString());
					updateBoard(dto);
				}
			}

		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return dto;
	}

	@Override
	public int countBoard(int category) throws SQLException {

		// select count(*) from board where categoroy = ?;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select count(*) from board where ";
		if (category == 1) {
			sql += "category = 1";
		} else {
			sql += "not category = 1";
		}
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return result;
	}


	@Override
	public List<BoardDTO> pagingBoard(int category, int startRow, int endRow) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// System.out.println("dao입니당");
		List<BoardDTO> list = null;

		String sql = "";
		if(category == 1)
		{
			sql = "select * from (select rownum R,A.* from(select * 	from board where category = 1 start with parent_no is null CONNECT BY  Prior board_no =parent_no ORDER SIBLINGS BY board_no DESC ) A) where R between ? and ?";
		}else
		{
			sql = "select * from (select rownum R,A.* from(select * 	from board where not category = 1 start with parent_no is null CONNECT BY  Prior board_no =parent_no ORDER SIBLINGS BY board_no DESC ) A) where R between ? and ?";
		}
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1,  startRow);
			ps.setInt(2, endRow );
			
			rs = ps.executeQuery();
			list = new ArrayList<>();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO(rs.getInt(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8),
						rs.getString(9), rs.getInt(10));
				list.add(dto);
			}
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return list;
	}
	
	/**
	 * 
	 *  select * from (select rownum R,A.* from(select * 	from board where category = 1 and writer_id like '%ki%'start with parent_no is null CONNECT BY  Prior board_no =parent_no ORDER SIBLINGS BY board_no DESC ) A) where R between 1 and 10;
	 */

	public List<BoardDTO> pagingSearchKeyField(String keyField, String keyWord, int category, int startRow, int endRow) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<BoardDTO> list = null;
		String sql = "select * from (select rownum R,A.* from(select * 	from board where  ";
		if (category == 1) {
			sql += "category=1 and ";
		} else {
			sql += "not category=1 and ";
		}
		if (keyField.equals("1")) {
			sql += "(writer_id like ? or title like ?)  ";
		} else if (keyField.equals("2")) {
			sql += "title like ? ";
		} else {
			sql += "writer_id like ?";
		}
		sql += " start with parent_no is null CONNECT BY  Prior board_no =parent_no ORDER SIBLINGS BY board_no DESC) A) where R between ? and ?";
		// System.out.println("keyword : " + keyWord);
		//System.out.println("sql : " + sql);
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);

			if (keyField.equals("1")) {
				ps.setString(1, "%" + keyWord + "%");
				ps.setString(2, "%" + keyWord + "%");
				ps.setInt(3, startRow);
				ps.setInt(4, endRow);
			} else {
				ps.setString(1, "%" + keyWord + "%");
				ps.setInt(2, startRow);
				ps.setInt(3, endRow);	
			}

			rs = ps.executeQuery();
			list = new ArrayList<>();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO(rs.getInt(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8),
						rs.getString(9), rs.getInt(10));
				list.add(dto);
			}
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public int countSearchBoard(int category, String keyField, String keyWord) throws SQLException {
		// select count(*) from board where not category =1 and writer_id like '%ki%';
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select count(*) from board where ";
		if (category == 1) {
			sql += "category = 1 ";
		} else {
			sql += "not category = 1 ";
		}
		if (keyField.equals("1")) {
			sql += "and (writer_id like ? or title like ?)  ";
		} else if (keyField.equals("2")) {
			sql += "and title like ? ";
		} else {
			sql += "and writer_id like ?";
		}
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			if (keyField.equals("1")) {
				ps.setString(1, "%" + keyWord + "%");
				ps.setString(2, "%" + keyWord + "%");
			} else {
				ps.setString(1, "%" + keyWord + "%");	
			}			
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			} finally {
			DbUtil.dbClose(con, ps, rs);
		}
			return result;
	}
}
