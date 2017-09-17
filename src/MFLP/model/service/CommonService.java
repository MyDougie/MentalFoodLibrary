package MFLP.model.service;

import java.sql.SQLException;

import MFLP.model.common.dao.CommonDAOImpl;
import MFLP.model.dto.MemberDTO;

public class CommonService {
	private static CommonDAOImpl commonDAOImpl = new CommonDAOImpl();

	public int idCheck(String id) throws SQLException {
		
		return commonDAOImpl.idCheck(id);
	}


	public int signUp(MemberDTO memberDTO) throws SQLException {
		
		return commonDAOImpl.signUp(memberDTO);
	}


	public int changeInfo(MemberDTO memberDTO) throws SQLException {
		
		return commonDAOImpl.changeInfo(memberDTO);
	}


	public int loginCheck(String id, String pw) throws SQLException {

		int result = commonDAOImpl.loginCheck(id, pw);

		return result;
	}
	
}
