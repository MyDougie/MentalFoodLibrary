package MFLP.controller.common.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MFLP.controller.Action;
import MFLP.model.dto.MemberDTO;
import MFLP.model.service.CommonService;

public class ChangeInfoAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int result;
		String id = request.getParameter("id");
		String pw = request.getParameter("newPassword");
		String name = request.getParameter("name");
		Date date = new Date(request.getParameter("date"));
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if(session.getAttribute("id") != null){
			
			MemberDTO memberDTO =new MemberDTO(id, name, pw, addr, sdf.format(date), tel, 0, 0);
			try {
				
				result = new CommonService().changeInfo(memberDTO);
				
				if(result == 1){
					response.sendRedirect("mflp");
				}else{
					response.sendRedirect("main.jsp?contentPage=/login/changeInfoForm.jsp");
				}
					
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}else{
			
			response.sendRedirect("mflp");	
		}
		
		
	}

}
