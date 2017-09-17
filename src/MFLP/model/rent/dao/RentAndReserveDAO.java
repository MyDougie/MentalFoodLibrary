package MFLP.model.rent.dao;

import java.sql.SQLException;
import java.util.List;

import MFLP.model.dto.RentDTO;
import MFLP.model.dto.ReserveDTO;

public interface RentAndReserveDAO {

	/**
	 * 대여
	 *  1) 예약자 있는 경우
	 *    - 날짜순으로 정렬해서 첫번째 예약자 = 사용자이고,
	 *    - 책 available = 0 이고,
	 *    - member의 rent_count<=2이면 
	 *    
	 *   -> 대여성공 : rent 테이블에 추가
	 *       (트리거 : reserve테이블에서 delete, book테이블 available = 1, user테이블 rent_count++)

	 *   
	 *  2) 예약자가 없는 경우
	 *    - 책 available = 0 이고,
	 *    - member의 rent_count<=2이면 대여가능
	 *    
	 *   -> 대여성공 : rent 테이블에 추가
	 *       (트리거 : book테이블 available = 1, user테이블 rent_count++)
	 * */
	int rent(String memberId, int bookNo, boolean flag) throws SQLException;
	
	
	/**
	 * 반납
	 *   - book 테이블 avaiable = 0
	 *   - member 테이블 rent_count --;
	 * */
	int returns(String memberId, int bookNo) throws SQLException;
	
	
	/**
	 * 사용자의 예약현황 조회
	 * */
	List<ReserveDTO> selectMyReserve(String memberId) throws SQLException;
	
	
	/**
	 * 예약
	 *  - available = 0 이면 예약불가
	 *  - getReservedCount() <= 3 이면 예약성공!(reserve 테이블에 추가)
	 * 
	 * @return 0 : 예약실패,  1 : 예약성공
	 * */
	int reserve(String memberId, int bookNo, int rentNo) throws SQLException;
	
		
	
	/**
	 * 예약취소
	 *  - reserve테이블에서 delete 
	 * @return 0: 취소실패, 1:취소성공 
	 * */
	int cancleReserve(int reserveNo, String memberId) throws SQLException;
	
	
	/**
	 * 책이 예약되어있는 수를 return (0~3)
	 * */
	int getReservedCount(int bookNo) throws SQLException;
	
	/**
	 * 책이 대여가능한 상태인지 return
	 * @return 0:이용가능, 1:대여중
	 * */
	boolean isAvailable(int bookNo) throws SQLException;
	
	
	/**
	 * 멤버의 대여 갯수를 return
	 * */
	int getRentCount(String memberId) throws SQLException;
	
	
	/**
	 * 첫번째 예약자가 맞는지를 return
	 * */
	public boolean isFirstReserver(String memberId, int bookNo) throws SQLException;
	
	/**
	 * 대여성공시에 함께 수행되는 trigger
	 * */
	public int rentTrigger(String memberId, int bookNo) throws SQLException;
	
	/**
	 * 나의 대여리스트를 return
	 * */
	public List<RentDTO> selectMyRent(String memberId) throws SQLException;
	
	/**
	 * 현재 대여되어있는 모든 리스트를 return
	 * */
	public List<RentDTO> selectAllRent() throws SQLException;
	
	/**
	 * 현재 예약되어있는 모든 리스트를 return
	 * */
	
	public List<ReserveDTO> selectAllReserve() throws SQLException;
	
	
	/**
	 * 해당 도서의 첫번째 예약자를 reserve테이블에서 delete
	 * */
	public int deleteFirstReserve(int bookNo) throws SQLException;
	
	/**
	 * 대여중인 책의 대여번호를 return.
	 * */
	public int getRentNo(int bookNo) throws SQLException;
	
	
	/**
	 * 책 연장하기
	 * */
	public int extent(int rentNo) throws SQLException;
	
	/**
	 * 책의 연장횟수 return
	 * */
	public int getExtensionCount(int rentNo) throws SQLException;
	
	/**
	 * 해당 사용자가 예약한 적이 있는지 return
	 * */
	public boolean hasReserve(String memberId, int bookNo) throws Exception;
	
	/**
	 * 조건검색 - 예약
	 * */
	public List<ReserveDTO> selectSearchReserve(String keyField, String keyWord) throws Exception;
	/**
	 * 조건검색 - 대여 
	 * */
	public List<RentDTO> selectSearchRent(String keyField, String keyWord, int startRow, int endRow) throws Exception;
	
	/**
	 * 대여수 return
	 * */
	public int rentCount(String keyField, String keyWord) throws Exception;
	/**
	 * 자신이 대여중인 책인지 return
	 * */
	public boolean isYourRent(String memberId, int bookNo) throws Exception;
	
}
