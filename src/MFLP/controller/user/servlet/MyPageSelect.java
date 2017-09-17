package MFLP.controller.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import MFLP.controller.Action;
import MFLP.model.dto.RentDTO;
import MFLP.model.dto.ReserveDTO;
import MFLP.model.service.MflpBookService;

public class MyPageSelect implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("id");
		
		try{
			if(memberId == null)
				throw new Exception("로그인해주세요");
			else{
				
				List<ReserveDTO> reserveList = MflpBookService.selectMyReserve(memberId);
				request.setAttribute("reserveList", reserveList);
				
				List<RentDTO> rentList = MflpBookService.selectMyRent(memberId);
				request.setAttribute("rentList", rentList);
				
				request.getRequestDispatcher("main.jsp?contentPage=/mypage/MypageSelect.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
