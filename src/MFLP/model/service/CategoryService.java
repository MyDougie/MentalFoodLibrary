package MFLP.model.service;

import java.sql.SQLException;
import java.util.List;

import MFLP.model.category.dao.CategoryDAOImpl;
import MFLP.model.dto.CategoryDTO;

public class CategoryService {
	private static CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();

	public int CategoryInsert(int no, String name) throws SQLException {
		
		return categoryDAOImpl.CategoryInsert(no, name);
	}


	public int CategoryUpdate(int no, String name) throws SQLException {
		
		return categoryDAOImpl.CategoryUpdate(no, name);
	}


	public int categoryDelete(int no) throws SQLException {
		
		return categoryDAOImpl.categoryDelete(no);
	}


	public List<CategoryDTO> categorySelectAll() throws SQLException {
		
		return categoryDAOImpl.categorySelectAll();
	}
	
	
}
