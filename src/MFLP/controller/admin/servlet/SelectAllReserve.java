package MFLP.controller.admin.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MFLP.controller.Action;
import MFLP.model.dto.RentDTO;
import MFLP.model.dto.ReserveDTO;
import MFLP.model.service.MflpBookService;

public class SelectAllReserve implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<ReserveDTO> allReserveList = MflpBookService.selectAllReserve();
			if(allReserveList == null || allReserveList.size()==0)
				throw new SQLException("예약현황이 없습니다.");
			request.setAttribute("allReserveList", allReserveList);
			request.getRequestDispatcher("main.jsp?contentPage=/admin/adminPage.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			/*
			 * request영역에 error메세지 저장하기
			 * */
		}
	}
	
}
