package MFLP.controller.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.BoardDTO;
import MFLP.model.service.BoardService;
import MFLP.model.service.CommentService;

public class BoardSelectAllAction implements Action {

	// �� ������ �� ������ �� ����
	private final static int pageSize = 10;

	// ������ �׷� �ȿ����� ������ ����
	private final static int pageGroupSize = 5;
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		try {
			String pageNum = request.getParameter("pageNum"); // ������ ��ȣ

			if (pageNum == null) {
				pageNum = "1";
			}
			
			if(request.getParameter("category")==null ||request.getParameter("category").equals(""))
			{
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('�߸��� ����Դϴ�.')");
				out.println("location.href='mflp'");
				out.println("</script>");
			}
			String category = request.getParameter("category"); //ī�װ�
			//System.out.println(category);
			int categoryInt = Integer.parseInt(category);
			int currentPage = Integer.parseInt(pageNum); // ���� ������
			int startRow = (currentPage - 1) * pageSize + 1; // ���� ������ ù��° ��
			int endRow = currentPage * pageSize; // ���� ������ ������ ��
			int count = 0;							//���� ������ ���� ��
			int number = 0;						//�۸�Ͽ� ǥ���� �۹�ȣ
			
				
			List<BoardDTO> list = new ArrayList<>();	
			count = BoardService.countBoard(categoryInt);
			if (count > 0) {
				if (endRow > count) {
					endRow = count;
				}
				list = BoardService.pagingBoard(categoryInt, startRow, endRow); // ���� �������� ����Ʈ�� �ҷ���
			} else {
				list = null;
			}
			number = count - (currentPage - 1) * pageSize;

			// ������ �׷��� ����

			// ex) pageGroupSize�� 3�� ��� '[1][2][3]'�� pageGroupCount
			// �� ��ŭ �ִ�.
			int pageGroupCount = count / (pageSize * pageGroupSize)
					+ (count % (pageSize * pageGroupSize) == 0 ? 0 : 1);
			// ������ �׷� ��ȣ
			// ex) pageGroupSize�� 3�� ��� '[1][2][3]'�� �������׷��ȣ�� 1 �̰�
			// '[2][3][4]'�� �������׷��ȣ�� 2 �̴�.
			int numPageGroup = (int) Math.ceil((double) currentPage / pageGroupSize);
			// �ش� �信�� ����� �Ӽ�
			
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
		} catch (SQLException e) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�߸��� ����Դϴ�.')");
			out.println("location.href='mflp'");
			out.println("</script>");
		}
    }


}
