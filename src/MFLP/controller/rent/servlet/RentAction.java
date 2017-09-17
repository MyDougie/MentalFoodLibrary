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
					throw new Exception("������ ��Ȯ���� �ʽ��ϴ�.");
				}
				
				int bookNo = Integer.parseInt(strBookNo);
				
				if(MflpBookService.isAvailable(bookNo)){  //å�� �̿밡���ϰ�
					
						if(MflpBookService.getRentCount(memberId) < 2){ //�뿩Ƚ�� 2 �̸��̸� �뿩
							
							if(MflpBookService.getReservedCount(bookNo) == 0){//å�� ������ ���� ���
								result = MflpBookService.rent(memberId, bookNo, true);
							}else{//å�� ������ �ִ� ���
								if(MflpBookService.isFirstReserver(memberId, bookNo)){//����ڰ� å�� ù��° �������̸�
									result = MflpBookService.rent(memberId, bookNo, false);
								} else {
									throw new Exception("ù��° �����ڰ� �ƴմϴ�.");
								}
							}

						} else {
							throw new Exception("�뿩Ƚ��2ȸ �ʰ� �Ұ���!!");
						}
				} else {
					throw new Exception("�뿩������ ������ �ƴմϴ�.");
				}
				
				if(result > 0 ){
					request.setAttribute("successMsg", "�뿩�Ǿ����ϴ�~");
				}else{
					throw new Exception("�뿩 ����!!!");
				}
						
			} catch (Exception e) {
				request.setAttribute("errorMsg", e.getMessage());
			}finally{
				request.setAttribute("tabState", 4);
				request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
			}
			
		
	}

}
