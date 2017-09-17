package MFLP.model.board.dao;

import java.sql.SQLException;
import java.util.List;

import MFLP.model.dto.BoardDTO;

public interface BoardDAO {
	
	
	BoardDTO boardSearchByNo(int no, boolean flag) throws SQLException;
	
	
	 /**
	  * 글 등록
	  *  -자유게시판, 공지게시판
	  *  -일반글, 답글
	  * */
	 int insertBoard(BoardDTO boardDTO) throws SQLException;
	/**
	 * 글 삭제
	 *  -자유게시판, 공지게시판
	 *  -일반글, 답글 	 
	 * */
	 int deleteBoard(String id, int boardNo) throws SQLException;
	/**
	 * 글 수정
	 *  -자유게시판, 공지게시판
	 *  -일반글, 답글
	 * */
	 int updateBoard(BoardDTO boardDTO) throws SQLException;

	 /**
	  * 
	  * 게시판 글의 총 숫자 출력
	  */
	 int countBoard(int category) throws SQLException;
	 int countSearchBoard (int category, String keyField, String keyWord) throws SQLException;
	 /**
	  * 페이징에서 띄워줄 것
	  */
	 List<BoardDTO> pagingBoard(int category, int startRow, int endRow) throws SQLException;
	 
	 /**
	  * 페이징 검색
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
