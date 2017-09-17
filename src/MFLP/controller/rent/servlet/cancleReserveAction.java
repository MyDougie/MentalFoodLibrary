package MFLP.controller.rent.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MFLP.controller.Action;
import MFLP.model.service.MflpBookService;

public class cancleReserveAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("id");
		
		int reserveNo = Integer.parseInt(request.getParameter("reserveNo"));
		PrintWriter out = response.getWriter();
		
		try{
			if(MflpBookService.cancleReserve(reserveNo, memberId) == 0){
				throw new Exception("예약취소 실패");
			}else{
				request.setAttribute("successMsg", "예약이 취소되었습니다~");
			}
		}catch(Exception e){
			request.setAttribute("errorMsg", e.getMessage());
		}
		
		request.setAttribute("tabState", 1);
		request.getRequestDispatcher("mflp?command=myPageSelect").forward(request, response);
		

	}

}
