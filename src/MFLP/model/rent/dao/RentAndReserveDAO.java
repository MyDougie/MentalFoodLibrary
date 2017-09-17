package MFLP.model.rent.dao;

import java.sql.SQLException;
import java.util.List;

import MFLP.model.dto.RentDTO;
import MFLP.model.dto.ReserveDTO;

public interface RentAndReserveDAO {

	/**
	 * �뿩
	 *  1) ������ �ִ� ���
	 *    - ��¥������ �����ؼ� ù��° ������ = ������̰�,
	 *    - å available = 0 �̰�,
	 *    - member�� rent_count<=2�̸� 
	 *    
	 *   -> �뿩���� : rent ���̺� �߰�
	 *       (Ʈ���� : reserve���̺��� delete, book���̺� available = 1, user���̺� rent_count++)

	 *   
	 *  2) �����ڰ� ���� ���
	 *    - å available = 0 �̰�,
	 *    - member�� rent_count<=2�̸� �뿩����
	 *    
	 *   -> �뿩���� : rent ���̺� �߰�
	 *       (Ʈ���� : book���̺� available = 1, user���̺� rent_count++)
	 * */
	int rent(String memberId, int bookNo, boolean flag) throws SQLException;
	
	
	/**
	 * �ݳ�
	 *   - book ���̺� avaiable = 0
	 *   - member ���̺� rent_count --;
	 * */
	int returns(String memberId, int bookNo) throws SQLException;
	
	
	/**
	 * ������� ������Ȳ ��ȸ
	 * */
	List<ReserveDTO> selectMyReserve(String memberId) throws SQLException;
	
	
	/**
	 * ����
	 *  - available = 0 �̸� ����Ұ�
	 *  - getReservedCount() <= 3 �̸� ���༺��!(reserve ���̺� �߰�)
	 * 
	 * @return 0 : �������,  1 : ���༺��
	 * */
	int reserve(String memberId, int bookNo, int rentNo) throws SQLException;
	
		
	
	/**
	 * �������
	 *  - reserve���̺��� delete 
	 * @return 0: ��ҽ���, 1:��Ҽ��� 
	 * */
	int cancleReserve(int reserveNo, String memberId) throws SQLException;
	
	
	/**
	 * å�� ����Ǿ��ִ� ���� return (0~3)
	 * */
	int getReservedCount(int bookNo) throws SQLException;
	
	/**
	 * å�� �뿩������ �������� return
	 * @return 0:�̿밡��, 1:�뿩��
	 * */
	boolean isAvailable(int bookNo) throws SQLException;
	
	
	/**
	 * ����� �뿩 ������ return
	 * */
	int getRentCount(String memberId) throws SQLException;
	
	
	/**
	 * ù��° �����ڰ� �´����� return
	 * */
	public boolean isFirstReserver(String memberId, int bookNo) throws SQLException;
	
	/**
	 * �뿩�����ÿ� �Բ� ����Ǵ� trigger
	 * */
	public int rentTrigger(String memberId, int bookNo) throws SQLException;
	
	/**
	 * ���� �뿩����Ʈ�� return
	 * */
	public List<RentDTO> selectMyRent(String memberId) throws SQLException;
	
	/**
	 * ���� �뿩�Ǿ��ִ� ��� ����Ʈ�� return
	 * */
	public List<RentDTO> selectAllRent() throws SQLException;
	
	/**
	 * ���� ����Ǿ��ִ� ��� ����Ʈ�� return
	 * */
	
	public List<ReserveDTO> selectAllReserve() throws SQLException;
	
	
	/**
	 * �ش� ������ ù��° �����ڸ� reserve���̺��� delete
	 * */
	public int deleteFirstReserve(int bookNo) throws SQLException;
	
	/**
	 * �뿩���� å�� �뿩��ȣ�� return.
	 * */
	public int getRentNo(int bookNo) throws SQLException;
	
	
	/**
	 * å �����ϱ�
	 * */
	public int extent(int rentNo) throws SQLException;
	
	/**
	 * å�� ����Ƚ�� return
	 * */
	public int getExtensionCount(int rentNo) throws SQLException;
	
	/**
	 * �ش� ����ڰ� ������ ���� �ִ��� return
	 * */
	public boolean hasReserve(String memberId, int bookNo) throws Exception;
	
	/**
	 * ���ǰ˻� - ����
	 * */
	public List<ReserveDTO> selectSearchReserve(String keyField, String keyWord) throws Exception;
	/**
	 * ���ǰ˻� - �뿩 
	 * */
	public List<RentDTO> selectSearchRent(String keyField, String keyWord, int startRow, int endRow) throws Exception;
	
	/**
	 * �뿩�� return
	 * */
	public int rentCount(String keyField, String keyWord) throws Exception;
	/**
	 * �ڽ��� �뿩���� å���� return
	 * */
	public boolean isYourRent(String memberId, int bookNo) throws Exception;
	
}
