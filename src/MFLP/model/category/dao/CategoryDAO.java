package MFLP.model.category.dao;

import java.sql.SQLException;
import java.util.List;

import MFLP.model.dto.CategoryDTO;

public interface CategoryDAO {
	//ī�װ� ��ü�˻�
	List<CategoryDTO> categorySelectAll() throws SQLException;
	//ī�װ� �߰�
	int CategoryInsert(int no, String name) throws SQLException;
	//ī�װ� ����
	int CategoryUpdate(int no, String name) throws SQLException;	
	//ī�װ� ����
	int categoryDelete(int no) throws SQLException;
}
