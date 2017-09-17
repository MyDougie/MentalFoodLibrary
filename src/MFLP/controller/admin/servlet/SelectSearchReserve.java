package MFLP.controller.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.model.dto.ReserveDTO;
import MFLP.model.service.MflpBookService;

@WebServlet("/SelectSearchReserve")
public class SelectSearchReserve extends HttpServlet {
    public SelectSearchReserve() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int opt = Integer.parseInt(request.getParameter("opt"));
		String keyWord = request.getParameter("keyWord");
		List<ReserveDTO> searchReserveList = null;
		try{
			if(opt == 0){
				searchReserveList = MflpBookService.selectSearchReserve("title", keyWord);
			}else if(opt == 1){
				searchReserveList = MflpBookService.selectSearchReserve("member_id", keyWord);
			}
			
			request.setAttribute("tabState", 1);
			request.setAttribute("searchReserveList", searchReserveList);
			
			request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
