package MFLP.controller.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MFLP.controller.Action;
import MFLP.model.dto.BoardDTO;
import MFLP.model.service.BoardService;

public class BoardDelete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session =  request.getSession();
			String id = (String)session.getAttribute("id");

			if(request.getParameter("boardNo").equals("") ||request.getParameter("boardNo")==null)
			{
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 경로입니다.')");
				out.println("location.href='mflp'");
				out.println("</script>");
			}
			int no = Integer.parseInt(request.getParameter("boardNo"));
			
			BoardDTO dto = BoardService.boardSearchByNo(no, false);
			BoardService.deleteBoard(id, no);
			int category = dto.getCategory();
			String url = "mflp?command=boardSelectAll&category=" + category;
			response.sendRedirect(url);
			
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로입니다.')");
			out.println("location.href='mflp'");
			out.println("</script>");
		}
	}

}
