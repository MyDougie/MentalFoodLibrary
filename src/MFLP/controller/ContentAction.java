package MFLP.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.model.dto.BoardDTO;
import MFLP.model.service.BoardService;

public class ContentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum = request.getParameter("pageNum"); // 페이지
										// 번호

			if (pageNum == null) {
				pageNum = "1";
			}

			int startRow = 1; // 현재 페이지 첫번째 줄
			int endRow = 3; // 현재 페이지 마지막 줄

			List<BoardDTO> bulletinList = new ArrayList<>();
			int bulletinCount = BoardService.countBoard(1);
			if (bulletinCount > 0) {
				if (endRow > bulletinCount) {
					endRow = bulletinCount;
				}
				bulletinList = BoardService.pagingBoard(1, startRow, endRow); // 현재
												// 페이지의
												// 리스트를
												// 불러옴
			} else {
				bulletinList = null;
			}

			List<BoardDTO> freeList = new ArrayList<>();
			int freeCount = BoardService.countBoard(2);
			if (freeCount > 0) {
				if (endRow > freeCount) {
					endRow = freeCount;
				}
				freeList = BoardService.pagingBoard(2, startRow, endRow); // 현재
												// 페이지의
												// 리스트를
												// 불러옴
			} else {
				freeList = null;
			}
			// System.out.println("bulletinList : " + bulletinList);
			// System.out.println("freeList : " + freeList);
			// 해당 뷰에서 사용할 속성
			// request.setAttribute("category", category);
			request.setAttribute("bulletinList", bulletinList);
			request.setAttribute("freeList", freeList);
			String url = "main.jsp?contentPage=content.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
