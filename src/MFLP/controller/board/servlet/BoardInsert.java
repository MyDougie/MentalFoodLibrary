package MFLP.controller.board.servlet;

import java.io.File;
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

public class BoardInsert implements Action {

	int categoryInt = 1;
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String savDir = request.getServletContext().getRealPath("/save");
	 		File file = new File(savDir);
			if(!file.exists())
			{
				file.mkdir(); 			//폴더 생성
			}
			int maxSize = 1024*1024*2;  //1b단위
			String encoding = "UTF-8";
			MultipartRequest m = new MultipartRequest(request, savDir, maxSize, encoding, new DefaultFileRenamePolicy());

			if(m.getParameter("title")==null || m.getParameter("content")==null||m.getParameter("category")==null
				||m.getParameter("title").equals("") || m.getParameter("content").equals("")||m.getParameter("category").equals("")
					)
			{
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 경로입니다.')");
				out.println("location.href='mflp'");
				out.println("</script>");
			}
			String title = m.getParameter("title");
			String content = m.getParameter("content");
			content = content.replaceAll("\r\n", "<br>");
			String category = m.getParameter("category");
			int parentNoInt = 0;
			
			if(!m.getParameter("parentNo").equals(""))
			{
				parentNoInt = Integer.parseInt(m.getParameter("parentNo"));
			}
			//System.out.println("content : " + content);
			//System.out.println("부모 번호 : " + parentNo);
			BoardDTO dto = new BoardDTO();
			
			categoryInt = 0;
			if (category == null) {
				categoryInt = 0;
			} else {
				categoryInt = Integer.parseInt(category);
			}
			
			String writerId = (String) request.getSession().getAttribute("id");


			String fName = m.getOriginalFileName("file");
			dto.setTitle(title);
			dto.setContent(content);
			dto.setCategory(categoryInt);
			dto.setWriterId(writerId);
			dto.setParentNo(parentNoInt);
			if(fName != null)
			{
				dto.setFileName(fName);
			}
			//System.out.println("Insert전 DTO : "  + dto);
			BoardService.insertBoard(dto);		
//			request.getRequestDispatcher(url).forward(request, response);			
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
