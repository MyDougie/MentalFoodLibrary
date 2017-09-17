package MFLP.controller.book.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.BookDTO;
import MFLP.model.service.MflpBookService;


public class SearchBySelectAction2 implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyField ="bookNo";
		String keyWord = request.getParameter("bookNo");
		
		try {
			List<BookDTO> list = new MflpBookService().searchBySelect(keyField, keyWord);
			if(request.getParameter("state")!=null){
				request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
			}
			
			if(list.size()==0){
				request.setAttribute("tabState", 3);
				request.setAttribute("errorMsg", "없는 책 번호 입니다.");
				request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
			}else{
			request.setAttribute("adminBookList", list);
		
				request.setAttribute("fName", list.get(0).getBookImg());
				request.setAttribute("tabState", 3);
				request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
