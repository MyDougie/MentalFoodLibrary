package MFLP.controller.admin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.service.CategoryService;

public class CategoryDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int categoryNo = 0;
			int result=0;	

		try {
			if(request.getParameter("categoryNo") != null || request.getParameter("categoryNo") != ""){
				categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
				result = new CategoryService().categoryDelete(categoryNo);
				System.out.println("접속");
				if(result==0){
					request.setAttribute("tabState", 5);
					request.setAttribute("errorMsg", "실패했습니다.");
					request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
				}else{
					
					request.setAttribute("tabState", 5);
					request.setAttribute("successMsg", "삭제완료.");
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
