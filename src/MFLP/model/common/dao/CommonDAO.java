package MFLP.model.common.dao;

import java.sql.SQLException;

import MFLP.model.dto.MemberDTO;

public interface CommonDAO {

	//아이디체크 1이면 존재함
	int idCheck(String id) throws SQLException;
	
	
	//회원가입
	int signUp(MemberDTO memberDTO) throws SQLException;
	
	//정보수정
	int changeInfo(MemberDTO memberDTO) throws SQLException;
	
	//로그인 관리자 일반유저 구분
	int loginCheck(String id, String pw) throws SQLException;
}
