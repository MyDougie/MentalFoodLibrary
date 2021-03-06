package MFLP.controller.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.BoardDTO;
import MFLP.model.service.BoardService;

public class BoardSearchByKeyField implements Action{

	// 한 페이지 당 보여줄 글 갯수
	private final static int pageSize = 10;

	// 페이지 그룹 안에서의 페이지 갯수
	private final static int pageGroupSize = 5;
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		try{
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			if(request.getParameter("keyField")==null ||request.getParameter("keyWord")==null||request.getParameter("pageNum")==null||request.getParameter("category")==null||
					request.getParameter("keyField")==null ||request.getParameter("keyWord").equals("")||request.getParameter("pageNum").equals("")||request.getParameter("category").equals("")
					)
			{
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 경로입니다.')");
				out.println("location.href='mflp'");
				out.println("</script>");			
			}
					
			String keyField = request.getParameter("keyField");
			String keyWord = request.getParameter("keyWord");
			String pageNum = request.getParameter("pageNum"); // 페이지 번호

			if (pageNum == null) {
				pageNum = "1";
			}
			String category = request.getParameter("category");
			int categoryInt = Integer.parseInt(category);

			//System.out.println("keyField : " + keyField);
			//System.out.println("keyWord : " + keyWord);
			//System.out.println("pageNum : " + pageNum);
			//System.out.println("category : " + category);
			
			int currentPage = Integer.parseInt(pageNum); // 현재 페이지
			int startRow = (currentPage - 1) * pageSize + 1; // 현재 페이지 첫번째 줄
			int endRow = currentPage * pageSize; // 현재 페이지 마지막 줄
			int count = 0;							//현재 페이지 글의 수
			int number = 0;						//글목록에 표시할 글번호
			
				
			List<BoardDTO> list = new ArrayList<>();	
			count = BoardService.countSearchBoard(categoryInt, keyField, keyWord);
			if (count > 0) {
				if (endRow > count) {
					endRow = count;
				}
				list = BoardService.searchKeyField(keyField, keyWord, categoryInt, startRow, endRow); // 현재 페이지의 리스트를 불러옴
			} else {
				list = null;
			}
			number = count - (currentPage - 1) * pageSize;

			// 페이지 그룹의 갯수

			// ex) pageGroupSize가 3일 경우 '[1][2][3]'가 pageGroupCount
			// 개 만큼 있다.
			int pageGroupCount = count / (pageSize * pageGroupSize)
					+ (count % (pageSize * pageGroupSize) == 0 ? 0 : 1);
			// 페이지 그룹 번호
			// ex) pageGroupSize가 3일 경우 '[1][2][3]'의 페이지그룹번호는 1 이고
			// '[2][3][4]'의 페이지그룹번호는 2 이다.
			int numPageGroup = (int) Math.ceil((double) currentPage / pageGroupSize);
			// 해당 뷰에서 사용할 속성
			request.setAttribute("category", category);
			request.setAttribute("currentPage", new Integer(currentPage));
			request.setAttribute("startRow", new Integer(startRow));
			request.setAttribute("endRow", new Integer(endRow));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("pageSize", new Integer(pageSize));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("pageGroupSize", new Integer(pageGroupSize));
			request.setAttribute("numPageGroup", new Integer(numPageGroup));
			request.setAttribute("pageGroupCount", new Integer(pageGroupCount));
			request.setAttribute("list", list);
			request.setAttribute("keyField", keyField);
			request.setAttribute("keyWord", keyWord);
			
			String url = "";

			if(category.equals("1"))
			{
				url = "main.jsp?contentPage=/board/bulletinBoard.jsp";
				
			}
			else
			{
				url = "main.jsp?contentPage=/board/freeBoard.jsp";
			}
			request.getRequestDispatcher(url).forward(request, response);
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
