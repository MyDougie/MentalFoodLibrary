package MFLP.model.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MFLP.model.dto.BoardCommentDTO;
import MFLP.model.util.DbUtil;

public class BoardCommentDAOImpl implements BoardCommentDAO {

	
	//select * from board_comment where board_no = 92 start with parent_no is null CONNECT BY  Prior comment_no =parent_no ORDER SIBLINGS BY comment_no asc;
	@Override
	public List<BoardCommentDTO> selectAllBoardComment( int boardNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BoardCommentDTO> list = null;
		String sql = "select * from board_comment where board_no = ? start with parent_no is null CONNECT BY  Prior comment_no =parent_no ORDER SIBLINGS BY comment_no asc";
		
	
		//System.out.println(boardNo + "no");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNo);
			rs = ps.executeQuery();
			list = new ArrayList<>();
			while (rs.next()) {
				BoardCommentDTO dto = new BoardCommentDTO(rs.getInt(1), rs.getString(2), 
						rs.getString(3),rs.getString(4), rs.getInt(5), rs.getInt(6));
				list.add(dto);
				//System.out.println("dto" +dto);
			}
			//System.out.println(list.size()+"listsize");
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return list;
	}

	@Override
	public int insertBoardComment(BoardCommentDTO boardCommentDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "";
		if(boardCommentDTO.getParentNo() != 0)
		{
			sql = "insert into board_comment values(comment_no.nextval, ?, sysdate, ?, ?, ?)";
		}
		else
		{
			sql = "insert into board_comment values(comment_no.nextval, ?, sysdate, ?, ?, null)";
		}
		//System.out.println("sql : "+ sql);
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			if(boardCommentDTO.getParentNo() != 0)
			{
				ps.setString(1, boardCommentDTO.getContent());
				ps.setString(2, boardCommentDTO.getMemberId());
				ps.setInt(3, boardCommentDTO.getBoardNo());
				ps.setInt(4, boardCommentDTO.getParentNo());
			}
			else
			{
				ps.setString(1, boardCommentDTO.getContent());
				ps.setString(2, boardCommentDTO.getMemberId());
				ps.setInt(3, boardCommentDTO.getBoardNo());
			}

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(con, ps, null);
		}
		return result;
	}

	@Override
	public int deleteBoardComment(String id, int commentNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete from board_comment where member_id=? and comment_no=?";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, commentNo);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(con, ps, null);
		}
		return result;
	}

	@Override
	public int updateBoardComment(BoardCommentDTO boardCommentDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "update board_comment set content=? where comment_no = ?";

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, boardCommentDTO.getContent());
			ps.setInt(2, boardCommentDTO.getCommentNo());

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(con, ps, null);
		}
		return result;
	}

	@Override
	public BoardCommentDTO selectByNoComment(int commentNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from board_comment where comment_no = ? ";
		BoardCommentDTO dto = null;
		//System.out.println(boardNo + "no");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, commentNo);
			rs = ps.executeQuery();
			if (rs.next()) 
			{
				dto = new BoardCommentDTO(rs.getInt(1), rs.getString(2), 
						rs.getString(3),rs.getString(4), rs.getInt(5),rs.getInt(6));
				//System.out.println("dto" +dto);
			}
			//System.out.println(list.size()+"listsize");
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return dto;
	}

}
