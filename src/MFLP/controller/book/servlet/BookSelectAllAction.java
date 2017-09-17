package MFLP.controller.book.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.BookDTO;
import MFLP.model.service.MflpBookService;

public class BookSelectAllAction implements Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	try {
			List<BookDTO> list = new MflpBookService().selectAll();
			
			request.setAttribute("list", list);

			request.getRequestDispatcher("main.jsp?contentPage=/book/bookList.jsp").forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }


}
