package MFLP.model.category.dao;

import java.sql.SQLException;
import java.util.List;

import MFLP.model.dto.CategoryDTO;

public interface CategoryDAO {
	//카테고리 전체검색
	List<CategoryDTO> categorySelectAll() throws SQLException;
	//카테고리 추가
	int CategoryInsert(int no, String name) throws SQLException;
	//카테고리 수정
	int CategoryUpdate(int no, String name) throws SQLException;	
	//카테고리 삭제
	int categoryDelete(int no) throws SQLException;
}
