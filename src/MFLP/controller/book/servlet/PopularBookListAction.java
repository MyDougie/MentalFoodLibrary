package MFLP.controller.book.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.BookDTO;
import MFLP.model.dto.populerBookDTO;
import MFLP.model.service.MflpBookService;

public class PopularBookListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		List<populerBookDTO> list = new MflpBookService().popularBookList();
		
		request.setAttribute("list", list);

		request.getRequestDispatcher("main.jsp?contentPage=/book/PopularBookList.jsp").forward(request, response);
	} catch (SQLException e) {
		
		e.printStackTrace();
	}

	}
}
