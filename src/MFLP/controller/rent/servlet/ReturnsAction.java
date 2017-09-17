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
				throw new Exception("������ ��Ȯ���� �ʽ��ϴ�.");
			
			int bookNo = Integer.parseInt(strBookNo);
			
			if(MflpBookService.returns(memberId, bookNo) == 0){
				throw new Exception("�ݳ� ����");
			}else{
				if(MflpBookService.getReservedCount(bookNo) != 0){//�ݳ� �������� ��, ������ ����� ������
					//3�� �� reserve���� ù��° ������ delete
				}
				request.setAttribute("successMsg", "�ݳ��Ǿ����ϴ�~");
			}
		
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.setAttribute("tabState", 4);
		request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
		
	}

}
