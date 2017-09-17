package MFLP.controller.comment.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.BoardCommentDTO;
import MFLP.model.service.BoardService;
import MFLP.model.service.CommentService;

public class CommentInsert implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			if(request.getParameter("comment_content") == null || request.getParameter("comment_content").equals("")
					||request.getParameter("comment_board") == null || request.getParameter("comment_board").equals("")
					||request.getParameter("parentNo") == null || request.getParameter("parentNo").equals(""))
			{
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 경로입니다.')");
				out.println("location.href='mflp'");
				out.println("</script>");
			}	
			
			String content = request.getParameter("comment_content");
			//System.out.println("인코딩 : " + content);
			String writer = (String) request.getSession().getAttribute("id");
			int boardNo = Integer.parseInt(request.getParameter("comment_board"));

			BoardCommentDTO dto = new BoardCommentDTO();
			dto.setContent(content);
			dto.setMemberId(writer);
			dto.setBoardNo(boardNo);
			if (request.getParameter("parentNo") != null) {
				dto.setParentNo(Integer.parseInt(request.getParameter("parentNo")));
			}

			CommentService.insertBoardComment(dto);
			String url = "mflp?command=boardSearchByNo&boardNo=" + boardNo;
			response.sendRedirect(url);
			//request.getRequestDispatcher(url).forward(request, response);
		} catch (SQLException e) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로입니다.')");
			out.println("location.href='mflp'");
			out.println("</script>");
		}

	}
}
