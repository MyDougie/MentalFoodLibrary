package MFLP.controller.comment.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xml.internal.dtm.DTMDOMException;

import MFLP.controller.Action;
import MFLP.model.dto.BoardCommentDTO;
import MFLP.model.dto.BoardDTO;
import MFLP.model.service.BoardService;
import MFLP.model.service.CommentService;

public class CommentDelete implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
			HttpSession session =  request.getSession();
			String id = (String)session.getAttribute("id");
			
			if(request.getParameter("commentNo") == null || request.getParameter("commentNo").equals(""))
			{
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 경로입니다.')");
				out.println("location.href='mflp'");
				out.println("</script>");
			}			

			int commentNo = Integer.parseInt(request.getParameter("commentNo"));
			BoardCommentDTO dto = new BoardCommentDTO();
			dto = CommentService.selectByNoComment(commentNo);
			int boardNo = dto.getBoardNo();

			CommentService.deleteBoardComment(id, commentNo);
			
			String url = "mflp?command=boardSearchByNo&boardNo="+boardNo;
			request.getRequestDispatcher(url).forward(request, response);
			
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로입니다.')");
			out.println("location.href='mflp'");
			out.println("</script>");
		}
	}

}
