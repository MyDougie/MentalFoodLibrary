package MFLP.model.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import MFLP.model.dto.MemberDTO;
import MFLP.model.util.DbUtil;

public class CommonDAOImpl implements CommonDAO {

	@Override
	public int idCheck(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = "select member_code from member where member_id = ?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);			
			ps.setString(1, id);			
			rs = ps.executeQuery();
			if(rs.next()){
				result = 1;
			};
		}catch (Exception e) {
			
		}finally{
			DbUtil.dbClose(con, ps, null);
		}
		return result;
	}

	@Override
	public int signUp(MemberDTO memberDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql ="insert into member values(?,?,?,?,?,?,0,0)";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, memberDTO.getMember());
			ps.setString(2, memberDTO.getName());
			ps.setString(3, memberDTO.getPassword());
			ps.setString(4, memberDTO.getAddr());
			ps.setString(5, memberDTO.getBirth());
			ps.setString(6, memberDTO.getPhone());
			result = ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DbUtil.dbClose(con, ps, null);
		}
		return result;
	}

	@Override
	public int changeInfo(MemberDTO memberDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String sql = "UPDATE member SET name = ? , password = ? , addr = ?, birth = ?, phone =? where member_id = ?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
		
			ps.setString(1, memberDTO.getName());
			ps.setString(2, memberDTO.getPassword());
			ps.setString(3, memberDTO.getAddr());
			ps.setString(4, memberDTO.getBirth());
			ps.setString(5, memberDTO.getPhone());
			ps.setString(6, memberDTO.getMember());
			
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.dbClose(con, ps, null);
		}
		return result;

	}

	@Override
	public int loginCheck(String id, String pw) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;

		String sql = "select member_code from member where member_id = ? and password = ?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, id);
			ps.setString(2, pw);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				if(rs.getInt(1)==0){
					result = 1;
					break;
				}else if (rs.getInt(1)==1){
					result = 2;
					break;
				}else{
					result = 0 ;
					break;
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DbUtil.dbClose(con, ps, null);
		}
		
		return result;
	}

}
