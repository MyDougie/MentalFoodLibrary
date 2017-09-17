package MFLP.controller.comment.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.BoardCommentDTO;
import MFLP.model.service.CommentService;

public class CommentUpdate implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			if(request.getParameter("commentNo") == null || request.getParameter("commentNo").equals("")
					||request.getParameter("content") == null || request.getParameter("content").equals(""))
			{
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 경로입니다.')");
				out.println("location.href='mflp'");
				out.println("</script>");
			}
			int commentNo = Integer.parseInt(request.getParameter("commentNo"));
			String content = request.getParameter("content");

			// System.out.println(commentNo + " : " + content);

			BoardCommentDTO dto = new BoardCommentDTO();
			dto.setCommentNo(commentNo);
			dto.setContent(content);

			int result = CommentService.updateBoardComment(dto);
			dto = CommentService.selectByNoComment(commentNo);
			int boardNo = dto.getBoardNo();
			
			response.sendRedirect("mflp?command=boardSearchByNo&boardNo=" + boardNo);
		}

		catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로입니다.')");
			out.println("location.href='mflp'");
			out.println("</script>");
		}

	}
}
