package MFLP.controller.common.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MFLP.controller.Action;
import MFLP.model.service.CommonService;

public class LoginAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("password");

		HttpSession session = request.getSession();
		String url = "mflp";
		int result = 0;
		CommonService commonService = new CommonService();
		try {
			result = commonService.loginCheck(id, pw);
			
			if (result == 0) {
				session.setAttribute("code", "실패");
				response.sendRedirect("login/loginForm.jsp");
			} else if (result == 1) {
				session.setAttribute("code", "일반");
				session.setAttribute("id", id);
				response.sendRedirect(url);
				
			} else if (result == 2) {
				session.setAttribute("code", "관리자");
				session.setAttribute("id", id);
				response.sendRedirect(url);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
