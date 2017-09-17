package MFLP.controller.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.MemberDTO;
import MFLP.model.service.CommonService;

public class SignUpAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		
		String url = "login/joinForm.jsp";
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String name = request.getParameter("name");
		Date date = new Date(request.getParameter("date"));
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		MemberDTO memberDTO = new MemberDTO();
		PrintWriter out = response.getWriter();
		if(id==null || pw ==null || name==null || date==null || tel==null || addr==null){
			response.sendRedirect("joinForm.jsp");
		}else{
			memberDTO = new MemberDTO(id, name, pw, addr, sdf.format(date), tel, 0, 0);
		}
		
		try {
			int result = new CommonService().signUp(memberDTO);
			if(result==1){
				url = "mflp?signUpState=1";
				//response.sendRedirect("mflp");
			//request.getRequestDispatcher("mflp").forward(request, response);
			}else{
				url = "login/joinForm.jsp";
				//request.getRequestDispatcher("login/joinForm.jsp").forward(request, response);
			}
		} catch (Exception e) {
			url = "login/joinForm.jsp";
		//	request.getRequestDispatcher("login/joinForm.jsp").forward(request, response);
		}
		response.sendRedirect(url);
		//request.getRequestDispatcher(url).forward(request, response);
	}

}
