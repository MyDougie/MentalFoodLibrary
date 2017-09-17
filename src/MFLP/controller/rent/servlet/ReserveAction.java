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
	            throw new Exception("�뿩���� ������ ���డ���մϴ�!");
	         if (MflpBookService.getReservedCount(bookNo) >= 3) 
	        	 throw new Exception("������ 3�� �� á���ϴ�!!");
	         if(MflpBookService.hasReserve(memberId, bookNo))
	        	 throw new Exception("�̹� ����Ǿ����ϴ�!!!");
	         if(MflpBookService.selectMyReserve(memberId).size() >=3)
	        	 throw new Exception("3�� �̻� ������ �� �����ϴ�!!!"); 
	         if(MflpBookService.isYourRent(memberId, bookNo))
	        	 throw new Exception("�ڽ��� �뿩�� å�� ������ �� �����ϴ�.");
	         
	         
	         //�뿩���� å�� �뿩��ȣ�� ������.
	         int rentNo = MflpBookService.getRentNo(bookNo);
	         if(MflpBookService.reserve(memberId, bookNo, rentNo) > 0){
	 			request.setAttribute("successMsg", "����Ǿ����ϴ�~");
	         }
	         else
	        	 throw new Exception("���� ����!!");
		}catch(Exception e){
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher("mflp?command=SearchBySelect&flag=0&bookNo="+bookNo).forward(request, response);
	}

}
