package MFLP.model.book.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import MFLP.model.dto.BookDTO;
import MFLP.model.dto.populerBookDTO;


public interface MflpBookDAO {
	//도서 전체 검색
	
	List<BookDTO> selectAll() throws SQLException;
	
	//도서 부분검색 키워드랑 키필드로 검색
	
	List<BookDTO> searchBySelect(String keyWord, String keyField) throws SQLException;
	
	//도서 추가 0실패 1 성공
	
	int bookInsert(BookDTO book) throws SQLException;
	
	//도서 수정
	
	int bookUpdate(BookDTO book) throws SQLException;
	
	//도서 삭제
	
	//인기 검색
	
	List<populerBookDTO> popularBookList() throws SQLException;

	int bookNum() throws Exception;
	
	// 책 전체 리스트 페이징
	
	public List<BookDTO> getBoardList(HashMap<String, Object> listOpt) throws SQLException;


	
}
