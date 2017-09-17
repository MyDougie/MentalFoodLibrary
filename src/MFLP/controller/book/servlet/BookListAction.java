package MFLP.controller.book.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.BookDTO;
import MFLP.model.service.MflpBookService;

public class BookListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	        
	        // ���� ������ ��ȣ �����
	        int spage = 1;
	        String page = request.getParameter("page");
	        
	        if(page != null)
	            spage = Integer.parseInt(page);
	        
	        // �˻����ǰ� �˻������� �����´�.
	        String opt = request.getParameter("opt");
	        String condition = request.getParameter("condition");
	      
	        // �˻����ǰ� ������ Map�� ��´�.
	        HashMap<String, Object> listOpt = new HashMap<String, Object>();
	        listOpt.put("opt", opt);
	        listOpt.put("condition", condition);
	        listOpt.put("start", spage*10-9);
	        
	        MflpBookService bookService = new MflpBookService();
	        int listCount;
			try {
				listCount = bookService.getBoardListCount(listOpt);
				List<BookDTO> list =  bookService.getBoardList(listOpt);
				
		        // �� ȭ�鿡 10���� �Խñ��� ����������
		        // ������ ��ȣ�� �� 5��, ���ķδ� [����]���� ǥ��
		        
		        // ��ü ������ ��
		        int maxPage = (int)(listCount/10.0 + 0.9);
		        //���� ������ ��ȣ
		        int startPage = (int)(spage/5.0 + 0.8) * 5 - 4;
		        //������ ������ ��ȣ
		        int endPage = startPage + 9;
		        if(endPage > maxPage)    endPage = maxPage;
		        
		        // 4�� ��������ȣ ����
		        request.setAttribute("spage", spage);
		        request.setAttribute("maxPage", maxPage);
		        request.setAttribute("startPage", startPage);
		        request.setAttribute("endPage", endPage);
		        
		        // ���� �� ���� �۸�� ����
		        request.setAttribute("listOpt", listOpt);
		        request.setAttribute("listCount", listCount);
		        request.setAttribute("list", list);
		      
		        request.getRequestDispatcher("main.jsp?contentPage=/book/bookListPage.jsp").forward(request, response);
			} catch (SQLException e) {				
				e.printStackTrace();
			} 	       
	    }
	}


