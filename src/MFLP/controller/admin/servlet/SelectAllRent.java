package MFLP.controller.admin.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MFLP.controller.Action;
import MFLP.model.dto.RentDTO;
import MFLP.model.service.MflpBookService;

public class SelectAllRent implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<RentDTO> allRentList = MflpBookService.selectAllRent();
			if(allRentList == null || allRentList.size()==0)
				throw new SQLException("대여현황이 없습니다.");
			request.setAttribute("allRentList", allRentList);
			request.getRequestDispatcher("mflp?command=selectAllReserve").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			/*
			 * request영역에 error메세지 저장하기
			 * */
		}
	}

}
