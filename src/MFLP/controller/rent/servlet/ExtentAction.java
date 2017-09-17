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
				throw new SQLException("����� ����� �־ ������ �� �����ϴ�!!");
			else if(MflpBookService.getExtensionCount(rentNo) >= 2){//����Ƚ���� 2ȸ�̻��̸� �Ұ�
				throw new SQLException("3ȸ �̻� ������ �� �����ϴ�.");
			}else{//����� ��� ���� ����Ƚ�� 3ȸ�̻� �ƴϸ�
				if(MflpBookService.extent(rentNo) == 0)
					throw new SQLException("���� ����!!");
				else{				
					request.setAttribute("successMsg", "���������� ����Ǿ����ϴ�~");
				}
			}
		} catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		
		request.getRequestDispatcher("mflp?command=myPageSelect").forward(request, response);
		
		
	}

}
