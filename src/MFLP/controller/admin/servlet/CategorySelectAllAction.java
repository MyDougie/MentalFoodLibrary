package MFLP.controller.admin.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.CategoryDTO;
import MFLP.model.service.CategoryService;

public class CategorySelectAllAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			List<CategoryDTO> list = new CategoryService().categorySelectAll();
			
			request.setAttribute("catedoryList", list);
			request.setAttribute("tabState", 5);
			request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

}
