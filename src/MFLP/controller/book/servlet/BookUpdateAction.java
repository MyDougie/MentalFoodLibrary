package MFLP.controller.book.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.BookDTO;
import MFLP.model.service.MflpBookService;

public class BookUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookNo = 0;
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String writer = request.getParameter("writer");
		
		String publisher = request.getParameter("publisher");
		int categoryNo = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
		if(request.getParameter("bookNo2") != null || title != null || description != null || writer != null || request.getParameter("writeDate") != null || publisher != null || publisher != null || request.getParameter("categoryNo33") != null){
			
			Date writeDate = new Date(request.getParameter("writeDate"));
			bookNo = Integer.parseInt(request.getParameter("bookNo2"));
			title = request.getParameter("title");
			description = request.getParameter("description");
			writer = request.getParameter("writer");
			publisher = request.getParameter("publisher");
			categoryNo = Integer.parseInt(request.getParameter("categoryNo33"));
			
			BookDTO bookDTO = new BookDTO(bookNo, title, description, writer, sdf.format(writeDate), publisher, 0, null, categoryNo);
			System.out.println(sdf.format(writeDate));
			int result = new MflpBookService().bookUpdate(bookDTO);
			System.out.println("���"+result);
			if(result==1){
				
				request.setAttribute("tabState", 3);
				request.setAttribute("successMsg", "�����Ϸ�.");
				request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
			}else{
				request.setAttribute("tabState", 3);
				request.setAttribute("errorMsg", "�����߽��ϴ�..");
				request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
			}
		}else{
			
			throw new Exception("���� �Է����ּ���.");
		}

		} catch (Exception e) {
			e.getStackTrace();
			request.setAttribute("tabState", 3);
			request.setAttribute("errorMsg", "���� �Է����ּ���");
			request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
		}
		
	}

}
