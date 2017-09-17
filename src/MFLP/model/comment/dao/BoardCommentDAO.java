package MFLP.model.comment.dao;

import java.sql.SQLException;
import java.util.List;

import MFLP.model.dto.BoardCommentDTO;
import MFLP.model.dto.BoardDTO;

public interface BoardCommentDAO {
	/**
	 * ��� ����Ʈ ���
	 * */
	List<BoardCommentDTO> selectAllBoardComment(int boardNo) throws SQLException;
	 /**
	  * ��� ��� 
	  * */
	 int insertBoardComment(BoardCommentDTO boardCommentDTO) throws SQLException;
	/**
	 * �� ����	 
	 * */
	 int deleteBoardComment(String id, int commentNo) throws SQLException;
	/**
	 * �� ����
	 * */
	 int updateBoardComment(BoardCommentDTO boardCommentDTO) throws SQLException;

	 /**
	  * boardno�� ���� ��� �˻�
	  * */
	 BoardCommentDTO selectByNoComment(int commentNo) throws SQLException;
	
}
