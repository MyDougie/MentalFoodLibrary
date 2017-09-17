package MFLP.controller.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.service.CommonService;

public class PasswordCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id =request.getParameter("id");
		String pw = request.getParameter("pw");
		
		try {
			int result = new CommonService().loginCheck(id, pw);
			
			PrintWriter out = response.getWriter();
			out.println(result);
			
		} catch (SQLException e) {


			e.printStackTrace();
		}

	}

}
