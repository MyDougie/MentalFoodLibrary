package MFLP.controller.rent.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.service.MflpBookService;

public class ExtentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		PrintWriter out = response.getWriter();
		
		
		try {
			
			int rentNo = MflpBookService.getRentNo(bookNo);
			System.out.println("rentNo : "+rentNo);
			if(MflpBookService.getReservedCount(bookNo) != 0)
				throw new SQLException("예약된 사람이 있어서 연장할 수 없습니다!!");
			else if(MflpBookService.getExtensionCount(rentNo) >= 2){//연장횟수가 2회이상이면 불가
				throw new SQLException("3회 이상 연장할 수 없습니다.");
			}else{//예약된 사람 없고 연장횟수 3회이상 아니면
				if(MflpBookService.extent(rentNo) == 0)
					throw new SQLException("연장 실패!!");
				else{				
					request.setAttribute("successMsg", "성공적으로 연장되었습니다~");
				}
			}
		} catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		
		request.getRequestDispatcher("mflp?command=myPageSelect").forward(request, response);
		
		
	}

}
