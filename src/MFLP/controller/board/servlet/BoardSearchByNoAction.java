package MFLP.controller.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.BoardCommentDTO;
import MFLP.model.dto.BoardDTO;
import MFLP.model.service.BoardService;
import MFLP.model.service.CommentService;

public class BoardSearchByNoAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			boolean flag = false;
			if(request.getParameter("flag") != null)
			{
				flag = true;
			}
			if(request.getParameter("boardNo")==null||request.getParameter("boardNo").equals(""))
			{
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 경로입니다.')");
				out.println("location.href='mflp'");
				out.println("</script>");			
			}
			
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			//int category = Integer.parseInt(request.getParameter("category"));
			List<BoardCommentDTO> list=null;
			//System.out.println(category);
			BoardDTO dto = BoardService.boardSearchByNo(boardNo, flag);
			//System.out.println(dto.getFileName());
			
			request.setAttribute("dto", dto);
			
			list = CommentService.selectAllBoardComment(boardNo);
			//System.out.println("commentList"+ list.toString()+"commetnselectall");
			request.setAttribute("commentList", list);
			request.getRequestDispatcher("main.jsp?contentPage=/board/boardContentView.jsp").forward(request, response);
		}
		catch(Exception e)
		{
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로입니다.')");
			out.println("location.href='mflp'");
			out.println("</script>");
		}		
	}

}
