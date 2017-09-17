package MFLP.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/mflp")
public class FrontController extends HttpServlet {
	Map<String, Action> actionName;

	public FrontController() {
	}

	public void init(ServletConfig config) throws ServletException {

		actionName = (Map<String, Action>) config.getServletContext().getAttribute("actionName");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");
		
		if (command == null) {
			command = "contentAction";
			// response.sendRedirect("main.jsp?contentPage=content.jsp");
		}
		actionName.get(command).execute(request, response);
	}

}
