package MFLP.model.book.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import MFLP.model.dto.BookDTO;
import MFLP.model.dto.populerBookDTO;


public interface MflpBookDAO {
	//���� ��ü �˻�
	
	List<BookDTO> selectAll() throws SQLException;
	
	//���� �κа˻� Ű����� Ű�ʵ�� �˻�
	
	List<BookDTO> searchBySelect(String keyWord, String keyField) throws SQLException;
	
	//���� �߰� 0���� 1 ����
	
	int bookInsert(BookDTO book) throws SQLException;
	
	//���� ����
	
	int bookUpdate(BookDTO book) throws SQLException;
	
	//���� ����
	
	//�α� �˻�
	
	List<populerBookDTO> popularBookList() throws SQLException;

	int bookNum() throws Exception;
	
	// å ��ü ����Ʈ ����¡
	
	public List<BookDTO> getBoardList(HashMap<String, Object> listOpt) throws SQLException;


	
}
