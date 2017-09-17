package MFLP.controller.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.model.dto.BoardDTO;
import MFLP.model.service.BoardService;


@WebServlet("/BoardUpdateTransport")
public class BoardUpdateTransport extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("boardNo") == null || request.getParameter("boardNo").equals(""))
		{
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로입니다.')");
			out.println("location.href='mflp'");
			out.println("</script>");
		}
		String no = request.getParameter("boardNo");
		//System.out.println(no);
		try {
			BoardDTO dto = BoardService.boardSearchByNo(Integer.parseInt(no), false);
			//System.out.println(dto);
			String content = dto.getContent().replaceAll("<br>","\r\n");
			dto.setContent(content);
			request.setAttribute("dto", dto);
			String url = "main.jsp?contentPage=/board/boardUpdateView.jsp" ;
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로입니다.')");
			out.println("location.href='mflp'");
			out.println("</script>");
		} 
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
