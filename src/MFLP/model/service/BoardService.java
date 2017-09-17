package MFLP.model.service;

import java.sql.SQLException;
import java.util.List;

import MFLP.model.board.dao.BoardDAOImpl;
import MFLP.model.dto.BoardDTO;

public class BoardService {
	private static BoardDAOImpl boardDao = new BoardDAOImpl();
	
	
	/**
	 * �Խñ� ���	
	 */
	public static BoardDTO boardSearchByNo(int no, boolean flag) throws SQLException{
		BoardDTO dto = boardDao.boardSearchByNo(no, flag);
		return dto;
	}
	/**
	 * �˻�  keyfield/keyword
	 * */
	public static List<BoardDTO> searchKeyField(String keyField, String keyWord, int category, int startRow, int endRow) throws SQLException{
		List<BoardDTO> list = boardDao.pagingSearchKeyField(keyField, keyWord, category, startRow, endRow);
		return list;
	}
	
	 /**
	  * �� ���
	  * */
	 public static int insertBoard(BoardDTO boardDTO) throws SQLException{
		 int result = boardDao.insertBoard(boardDTO);
		 return result;
	 }
	/**
	 * �� ����
	 * */
	 public static int deleteBoard(String id, int boardNo) throws SQLException{
		 int result = boardDao.deleteBoard(id, boardNo);
		 return result;
	 }
	/**
	 * �� ����
	 * */
	 public static int updateBoard(BoardDTO boardDTO) throws SQLException{
		 int result = boardDao.updateBoard(boardDTO);
		 return result;
	 }

	 public static int countBoard (int category) throws SQLException{
		int result = boardDao.countBoard(category);
		return result;
	 }
	 
	 public static int countSearchBoard (int category, String keyField, String keyWord) throws SQLException
	 {
		 int result = boardDao.countSearchBoard(category, keyField, keyWord);
		 return result;
	 }
	 
	 public static List<BoardDTO> pagingBoard (int category, int startRow, int endRow) throws SQLException
	 {
		List<BoardDTO> list = boardDao.pagingBoard(category, startRow, endRow);
		return list;
	 }
}
