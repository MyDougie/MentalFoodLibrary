package MFLP.model.common.dao;

import java.sql.SQLException;

import MFLP.model.dto.MemberDTO;

public interface CommonDAO {

	//���̵�üũ 1�̸� ������
	int idCheck(String id) throws SQLException;
	
	
	//ȸ������
	int signUp(MemberDTO memberDTO) throws SQLException;
	
	//��������
	int changeInfo(MemberDTO memberDTO) throws SQLException;
	
	//�α��� ������ �Ϲ����� ����
	int loginCheck(String id, String pw) throws SQLException;
}
