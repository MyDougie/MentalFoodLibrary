package MFLP.controller.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.model.dto.BookDTO;
import MFLP.model.service.MflpBookService;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class RentStateServlet
 */
@WebServlet("/RentStateServlet")
public class RentStateServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyField = "bookNo";
		String keyWord = request.getParameter("keyWord");
		System.out.println(keyWord);
		try {

			List<BookDTO> list = new MflpBookService().searchBySelect(keyField, keyWord);

			request.setAttribute("list", list);
			System.out.println(list.size());
			// 출력스트림
			PrintWriter out = response.getWriter();
 
			JSONArray jsonArr = JSONArray.fromObject(list);
			out.println(jsonArr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
