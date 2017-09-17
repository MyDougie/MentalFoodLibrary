package MFLP.controller.book.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.BookDTO;
import MFLP.model.service.MflpBookService;

public class BookListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	        
	        // 현재 페이지 번호 만들기
	        int spage = 1;
	        String page = request.getParameter("page");
	        
	        if(page != null)
	            spage = Integer.parseInt(page);
	        
	        // 검색조건과 검색내용을 가져온다.
	        String opt = request.getParameter("opt");
	        String condition = request.getParameter("condition");
	      
	        // 검색조건과 내용을 Map에 담는다.
	        HashMap<String, Object> listOpt = new HashMap<String, Object>();
	        listOpt.put("opt", opt);
	        listOpt.put("condition", condition);
	        listOpt.put("start", spage*10-9);
	        
	        MflpBookService bookService = new MflpBookService();
	        int listCount;
			try {
				listCount = bookService.getBoardListCount(listOpt);
				List<BookDTO> list =  bookService.getBoardList(listOpt);
				
		        // 한 화면에 10개의 게시글을 보여지게함
		        // 페이지 번호는 총 5개, 이후로는 [다음]으로 표시
		        
		        // 전체 페이지 수
		        int maxPage = (int)(listCount/10.0 + 0.9);
		        //시작 페이지 번호
		        int startPage = (int)(spage/5.0 + 0.8) * 5 - 4;
		        //마지막 페이지 번호
		        int endPage = startPage + 9;
		        if(endPage > maxPage)    endPage = maxPage;
		        
		        // 4개 페이지번호 저장
		        request.setAttribute("spage", spage);
		        request.setAttribute("maxPage", maxPage);
		        request.setAttribute("startPage", startPage);
		        request.setAttribute("endPage", endPage);
		        
		        // 글의 총 수와 글목록 저장
		        request.setAttribute("listOpt", listOpt);
		        request.setAttribute("listCount", listCount);
		        request.setAttribute("list", list);
		      
		        request.getRequestDispatcher("main.jsp?contentPage=/book/bookListPage.jsp").forward(request, response);
			} catch (SQLException e) {				
				e.printStackTrace();
			} 	       
	    }
	}


