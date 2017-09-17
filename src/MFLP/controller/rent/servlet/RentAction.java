package MFLP.controller.rent.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.service.MflpBookService;

public class RentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String strBookNo =request.getParameter("bookNo"); 
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int result;
		
			try {
				if(memberId == null || strBookNo == null  || memberId.equals("") || strBookNo.equals("")){
					throw new Exception("정보가 정확하지 않습니다.");
				}
				
				int bookNo = Integer.parseInt(strBookNo);
				
				if(MflpBookService.isAvailable(bookNo)){  //책이 이용가능하고
					
						if(MflpBookService.getRentCount(memberId) < 2){ //대여횟수 2 미만이면 대여
							
							if(MflpBookService.getReservedCount(bookNo) == 0){//책에 예약이 없는 경우
								result = MflpBookService.rent(memberId, bookNo, true);
							}else{//책에 예약이 있는 경우
								if(MflpBookService.isFirstReserver(memberId, bookNo)){//사용자가 책의 첫번째 예약자이면
									result = MflpBookService.rent(memberId, bookNo, false);
								} else {
									throw new Exception("첫번째 예약자가 아닙니다.");
								}
							}

						} else {
							throw new Exception("대여횟수2회 초과 불가능!!");
						}
				} else {
					throw new Exception("대여가능한 도서가 아닙니다.");
				}
				
				if(result > 0 ){
					request.setAttribute("successMsg", "대여되었습니다~");
				}else{
					throw new Exception("대여 실패!!!");
				}
						
			} catch (Exception e) {
				request.setAttribute("errorMsg", e.getMessage());
			}finally{
				request.setAttribute("tabState", 4);
				request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
			}
			
		
	}

}
