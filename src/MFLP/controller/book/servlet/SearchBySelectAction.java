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


public class SearchBySelectAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyField ="bookNo";
		String keyWord = request.getParameter("bookNo");
		String flag = request.getParameter("flag");
		try {
			List<BookDTO> list = new MflpBookService().searchBySelect(keyField, keyWord);
			request.setAttribute("list", list);
			if(flag == null)
				request.getRequestDispatcher("main.jsp?contentPage=/admin/rentState.jsp").forward(request, response);
			else{
				request.setAttribute("fName", list.get(0).getBookImg());
				request.getRequestDispatcher("main.jsp?contentPage=/book/bookDetail.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
