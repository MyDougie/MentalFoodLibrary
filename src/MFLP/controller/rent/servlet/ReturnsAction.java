package MFLP.controller.rent.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.service.MflpBookService;

public class ReturnsAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String strBookNo = request.getParameter("bookNo");
		PrintWriter out = response.getWriter();
		
		try{
			if(memberId == null || strBookNo == null || memberId.equals("") || strBookNo.equals(""))
				throw new Exception("정보가 정확하지 않습니다.");
			
			int bookNo = Integer.parseInt(strBookNo);
			
			if(MflpBookService.returns(memberId, bookNo) == 0){
				throw new Exception("반납 실패");
			}else{
				if(MflpBookService.getReservedCount(bookNo) != 0){//반납 성공했을 때, 예약한 사람이 있으면
					//3일 뒤 reserve에서 첫번째 예약자 delete
				}
				request.setAttribute("successMsg", "반납되었습니다~");
			}
		
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.setAttribute("tabState", 4);
		request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
		
	}

}
