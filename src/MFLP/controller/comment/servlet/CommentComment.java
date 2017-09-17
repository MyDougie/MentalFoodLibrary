package MFLP.controller.comment.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.BoardCommentDTO;
import MFLP.model.service.CommentService;

public class CommentComment implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if(request.getParameter("parentNo") == null || request.getParameter("parentNo").equals("")
					||request.getParameter("parentNo")==null||request.getParameter("parentNo").equals("")
					||request.getParameter("content")==null||request.getParameter("content").equals(""))
			{
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 경로입니다.')");
				out.println("location.href='mflp'");
				out.println("</script>");
			}			
			int parentNo = Integer.parseInt(request.getParameter("parentNo"));
			
			BoardCommentDTO parentDTO = CommentService.selectByNoComment(parentNo);
			
			int boardNo = parentDTO.getBoardNo();
			String content = request.getParameter("content");
			String memberId = (String) request.getSession().getAttribute("id");
			
			BoardCommentDTO dto = new BoardCommentDTO();
			dto.setParentNo(parentNo);
			dto.setContent(content);
			dto.setMemberId(memberId);
			dto.setBoardNo(boardNo);
	
			//System.out.println("dto : " + dto);
			int result = CommentService.insertBoardComment(dto);
			//System.out.println("result : " + result);
			PrintWriter out = response.getWriter();
			
			response.sendRedirect("mflp?command=boardSearchByNo&boardNo=" + boardNo);
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로입니다.')");
			out.println("location.href='mflp'");
			out.println("</script>");
		}
	}
}
