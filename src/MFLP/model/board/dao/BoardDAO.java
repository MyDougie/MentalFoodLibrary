package MFLP.model.board.dao;

import java.sql.SQLException;
import java.util.List;

import MFLP.model.dto.BoardDTO;

public interface BoardDAO {
	
	
	BoardDTO boardSearchByNo(int no, boolean flag) throws SQLException;
	
	
	 /**
	  * �� ���
	  *  -�����Խ���, �����Խ���
	  *  -�Ϲݱ�, ���
	  * */
	 int insertBoard(BoardDTO boardDTO) throws SQLException;
	/**
	 * �� ����
	 *  -�����Խ���, �����Խ���
	 *  -�Ϲݱ�, ��� 	 
	 * */
	 int deleteBoard(String id, int boardNo) throws SQLException;
	/**
	 * �� ����
	 *  -�����Խ���, �����Խ���
	 *  -�Ϲݱ�, ���
	 * */
	 int updateBoard(BoardDTO boardDTO) throws SQLException;

	 /**
	  * 
	  * �Խ��� ���� �� ���� ���
	  */
	 int countBoard(int category) throws SQLException;
	 int countSearchBoard (int category, String keyField, String keyWord) throws SQLException;
	 /**
	  * ����¡���� ����� ��
	  */
	 List<BoardDTO> pagingBoard(int category, int startRow, int endRow) throws SQLException;
	 
	 /**
	  * ����¡ �˻�
	  * @param keyField
	  * @param keyWord
	  * @param category
	  * @param startRow
	  * @param endRow
	  * @return
	  * @throws SQLException
	  */
	 public List<BoardDTO> pagingSearchKeyField(String keyField, String keyWord, int category, int startRow, int endRow) throws SQLException;
}
