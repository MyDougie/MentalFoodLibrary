package MFLP.controller.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import MFLP.controller.Action;
import MFLP.model.dto.BoardDTO;
import MFLP.model.service.BoardService;

public class BoardUpdate implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			String savDir = request.getServletContext().getRealPath("/save");
			int maxSize = 1024*1024*2;  //1b단위
			String encoding = "UTF-8";
			MultipartRequest m = new MultipartRequest(request, savDir, maxSize, encoding, new DefaultFileRenamePolicy());
			
			if(m.getParameter("title")==null || m.getParameter("content")==null||m.getParameter("category")==null
		||m.getParameter("boardNo")==null||m.getParameter("fileName")==null||
		m.getParameter("title").equals("")|| m.getParameter("content").equals("")||m.getParameter("category").equals("")
		||m.getParameter("boardNo").equals("")||m.getParameter("fileName").equals(""))
			{
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 경로입니다.')");
				out.println("location.href='mflp'");
				out.println("</script>");
			}
			
			String title = m.getParameter("title");
			String content = m.getParameter("content");
			String category = m.getParameter("category");
			String boardNo = m.getParameter("boardNo");
			String fileName = m.getParameter("fileName");
			content = content.replaceAll("\r\n", "<br>");
			BoardDTO dto = new BoardDTO();
			
			int categoryInt = 0;
			if (category == null) {
				categoryInt = 0;
			} else {
				categoryInt = Integer.parseInt(category);
			}

			String writerId = (String) request.getSession().getAttribute("id");

			dto.setBoardNo(Integer.parseInt(boardNo));
			dto.setTitle(title);
			dto.setContent(content);
			dto.setCategory(categoryInt);
			dto.setWriterId(writerId);

			String fName = m.getOriginalFileName("file");
			if(fileName!=null)
			{
				dto.setFileName(fileName);
			}
			if(fName != null)
			{
				dto.setFileName(fName);
			}

			BoardService.updateBoard(dto);
			//System.out.println("업데이트 결과 : "+ result);
			String url = "mflp?command=boardSelectAll&category=" + category;	
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
