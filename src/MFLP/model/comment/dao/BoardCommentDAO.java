package MFLP.model.comment.dao;

import java.sql.SQLException;
import java.util.List;

import MFLP.model.dto.BoardCommentDTO;
import MFLP.model.dto.BoardDTO;

public interface BoardCommentDAO {
	/**
	 * 댓글 리스트 출력
	 * */
	List<BoardCommentDTO> selectAllBoardComment(int boardNo) throws SQLException;
	 /**
	  * 댓글 등록 
	  * */
	 int insertBoardComment(BoardCommentDTO boardCommentDTO) throws SQLException;
	/**
	 * 글 삭제	 
	 * */
	 int deleteBoardComment(String id, int commentNo) throws SQLException;
	/**
	 * 글 수정
	 * */
	 int updateBoardComment(BoardCommentDTO boardCommentDTO) throws SQLException;

	 /**
	  * boardno에 따른 댓글 검색
	  * */
	 BoardCommentDTO selectByNoComment(int commentNo) throws SQLException;
	
}
