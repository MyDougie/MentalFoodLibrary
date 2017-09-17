package MFLP.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import MFLP.model.book.dao.MflpBookDAOImpl;
import MFLP.model.dto.BookDTO;
import MFLP.model.dto.RentDTO;
import MFLP.model.dto.ReserveDTO;
import MFLP.model.dto.populerBookDTO;
import MFLP.model.rent.dao.RentAndReserveDAOImpl;
	

public class MflpBookService {
	private static MflpBookDAOImpl bookDAOImpl = new MflpBookDAOImpl();
	private static RentAndReserveDAOImpl rentDAOImpl = new RentAndReserveDAOImpl();
	
	public List<BookDTO> selectAll() throws SQLException{
		
		List<BookDTO> list = bookDAOImpl.selectAll();
		
		
		return list;
	}
	
	//도서 부분검색 키워드랑 키필드로 검색
	
	public List<BookDTO> searchBySelect(String keyWord, String keyField) throws SQLException{
		List<BookDTO> list = bookDAOImpl.searchBySelect(keyField, keyWord);
	      
	      return list;
	}
	
	//도서 추가 0실패 1 성공
	
	 public static int bookInsert(BookDTO book) throws SQLException{
	      return bookDAOImpl.bookInsert(book);
	       
	   }
	 
	// 페이지 리스트
		public List<BookDTO> getBoardList(HashMap<String, Object> listOpt) throws SQLException {
			List<BookDTO> list = bookDAOImpl.getBoardList(listOpt);
			
			return list;
		}

		// 페이지 갯수
		public int getBoardListCount(HashMap<String, Object> listOpt) throws SQLException {
			return bookDAOImpl.getBoardListCount(listOpt);
		}
	
	//도서 수정
	 
	
	public int bookUpdate(BookDTO book) throws SQLException{
		return bookDAOImpl.bookUpdate(book);
	}
	
	//도서 삭제
	
	//인기 검색
	
	public List<populerBookDTO> popularBookList() throws SQLException{
	
		return bookDAOImpl.popularBookList();
	}
	
	public static int rent(String memberId, int bookNo, boolean flag) throws SQLException{
		return rentDAOImpl.rent(memberId, bookNo, flag);
	}
	
	
	public static int returns(String memberId, int bookNo) throws SQLException{
		return rentDAOImpl.returns(memberId, bookNo);
	}
	
	
	public static List<ReserveDTO> selectMyReserve(String memberId) throws SQLException{
		return rentDAOImpl.selectMyReserve(memberId);
	}
	
	public static List<RentDTO> selectMyRent(String memberId) throws SQLException{
		return rentDAOImpl.selectMyRent(memberId);
	}
	
	public static List<RentDTO> selectAllRent() throws SQLException{
		return rentDAOImpl.selectAllRent();
	}
	public static List<ReserveDTO> selectAllReserve() throws SQLException{
		return rentDAOImpl.selectAllReserve();
	}
	
	public static int reserve(String memberId, int bookNo, int rentNo) throws SQLException{
		return rentDAOImpl.reserve(memberId, bookNo, rentNo);
	}
	
	
	
	public static int cancleReserve(int reserveNo, String memberId) throws SQLException{
		return rentDAOImpl.cancleReserve(reserveNo, memberId);
		
	}
	
	
	public static int getReservedCount(int bookNo) throws SQLException{
		return rentDAOImpl.getReservedCount(bookNo);
	}
	
	public static boolean isAvailable(int bookNo) throws SQLException{
		return rentDAOImpl.isAvailable(bookNo);
	}
	
	
	
	public static int getRentCount(String memberId) throws SQLException{
		return rentDAOImpl.getRentCount(memberId);
	}
	
	
	public static boolean isFirstReserver(String memberId, int bookNo) throws SQLException{
		return rentDAOImpl.isFirstReserver(memberId, bookNo);
	}
	
	public static int getRentNo(int bookNo) throws SQLException{
		return rentDAOImpl.getRentNo(bookNo);
	}
	
	public static int extent(int rentNo) throws SQLException{
		return rentDAOImpl.extent(rentNo);
	}
	
	public static int getExtensionCount(int rentNo) throws SQLException{
		return rentDAOImpl.getExtensionCount(rentNo);
	}
	
	public static boolean hasReserve(String memberId, int bookNo) throws Exception{
		return rentDAOImpl.hasReserve(memberId, bookNo);
	}
	/////
	public static int bookNum() throws Exception {
		int result = bookDAOImpl.bookNum();
		return result;
	}
	
	public static List<ReserveDTO> selectSearchReserve(String keyField, String keyWord) throws Exception{
		return rentDAOImpl.selectSearchReserve(keyField, keyWord);
	}

	public static List<RentDTO> selectSearchRent(String keyField, String keyWord, int startRow, int endRow) throws Exception {
		return rentDAOImpl.selectSearchRent(keyField, keyWord, startRow, endRow);
	}
	
	public static int rentCount(String keyField, String keyWord) throws Exception{
		return rentDAOImpl.rentCount(keyField, keyWord);
	}

	public static boolean isYourRent(String memberId, int bookNo)throws Exception {
		return rentDAOImpl.isYourRent(memberId, bookNo);
	}



}
