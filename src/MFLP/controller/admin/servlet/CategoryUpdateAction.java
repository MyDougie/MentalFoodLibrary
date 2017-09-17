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

public class CategoryUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int result = 0;
		try {
			if(request.getParameter("categoryNo") != null || request.getParameter("categoryNo") != ""){
			int	categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
			String categoryName = request.getParameter("categoryName");
				result = new CategoryService().CategoryUpdate(categoryNo, categoryName);
				if(result==0){
					request.setAttribute("tabState", 5);
					request.setAttribute("errorMsg", "실패했습니다.");
					request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
				}else{
					
					request.setAttribute("tabState", 5);
					request.setAttribute("successMsg", "수정완료.");
					request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
				}
			}else{
				throw new Exception("값을 입력해주세요.");
			}
			
		} catch (Exception e) {
			
			request.setAttribute("tabState", 5);
			request.setAttribute("errorMsg", "값을 입력해주세요.");
			request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
		}

	}

}
