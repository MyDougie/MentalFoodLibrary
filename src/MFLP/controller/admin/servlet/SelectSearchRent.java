package MFLP.controller.admin.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.model.dto.BoardDTO;
import MFLP.model.dto.RentDTO;
import MFLP.model.dto.ReserveDTO;
import MFLP.model.service.BoardService;
import MFLP.model.service.MflpBookService;

@WebServlet("/SelectSearchRent")
public class SelectSearchRent extends HttpServlet {
	// 한 페이지 당 보여줄 글 갯수
	private final static int pageSize = 10;

	// 페이지 그룹 안에서의 페이지 갯수
	private final static int pageGroupSize = 5;
		
    public SelectSearchRent() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    	
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int opt = Integer.parseInt(request.getParameter("opt"));
		String keyWord = request.getParameter("keyWord");
		List<RentDTO> searchRentList = null;
		String keyField = null;
		
		/*if(opt == 0){
			keyField = "title";
		}else if(opt == 1){
			keyField = "member_id";
		}*/
		keyField = (opt == 0) ? "title" : "member_id";
		
		try{
			/////////////////////
			String pageNum = request.getParameter("pageNum"); // 페이지 번호

			if (pageNum == null) {
				pageNum = "1";
			}
			
			int currentPage = Integer.parseInt(pageNum); // 현재 페이지
			int startRow = (currentPage - 1) * pageSize + 1; // 현재 페이지 첫번째 줄
			int endRow = currentPage * pageSize; // 현재 페이지 마지막 줄
			int count = 0;							//현재 페이지 글의 수
			int number = 0;						//글목록에 표시할 글번호
			
			count = MflpBookService.rentCount(keyField, keyWord);
			if (count > 0) {
				if (endRow > count) {
					endRow = count;
				}
				searchRentList = MflpBookService.selectSearchRent(keyField, keyWord, startRow, endRow);
				//list = MflpBookService.pagingBoard(categoryInt, startRow, endRow); // 현재 페이지의 리스트를 불러옴
			} else {
				searchRentList = null;
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
			request.setAttribute("opt", opt);
			request.setAttribute("currentPage", new Integer(currentPage));
			request.setAttribute("startRow", new Integer(startRow));
			request.setAttribute("endRow", new Integer(endRow));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("pageSize", new Integer(pageSize));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("pageGroupSize", new Integer(pageGroupSize));
			request.setAttribute("numPageGroup", new Integer(numPageGroup));
			request.setAttribute("pageGroupCount", new Integer(pageGroupCount));
			
			request.setAttribute("keyField", keyField);
			request.setAttribute("keyWord", keyWord);
			request.setAttribute("searchRentList", searchRentList);
			/////////////////////
			System.out.println("pageGroupSize : " + pageGroupSize);
			System.out.println("numPageGroup : "+ numPageGroup);
			System.out.println("currentPage : "+ currentPage);
			request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
