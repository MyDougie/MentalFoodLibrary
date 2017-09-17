package MFLP.controller.rent.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MFLP.controller.Action;
import MFLP.model.service.MflpBookService;

public class ReserveAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("id");
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		
		PrintWriter out = response.getWriter();
		try{
			if(MflpBookService.isAvailable(bookNo))
	            throw new Exception("대여중인 도서만 예약가능합니다!");
	         if (MflpBookService.getReservedCount(bookNo) >= 3) 
	        	 throw new Exception("예약이 3명 다 찼습니다!!");
	         if(MflpBookService.hasReserve(memberId, bookNo))
	        	 throw new Exception("이미 예약되었습니다!!!");
	         if(MflpBookService.selectMyReserve(memberId).size() >=3)
	        	 throw new Exception("3권 이상 예약할 수 없습니다!!!"); 
	         if(MflpBookService.isYourRent(memberId, bookNo))
	        	 throw new Exception("자신이 대여한 책에 예약할 수 없습니다.");
	         
	         
	         //대여중인 책의 대여번호를 가져옴.
	         int rentNo = MflpBookService.getRentNo(bookNo);
	         if(MflpBookService.reserve(memberId, bookNo, rentNo) > 0){
	 			request.setAttribute("successMsg", "예약되었습니다~");
	         }
	         else
	        	 throw new Exception("예약 실패!!");
		}catch(Exception e){
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher("mflp?command=SearchBySelect&flag=0&bookNo="+bookNo).forward(request, response);
	}

}
