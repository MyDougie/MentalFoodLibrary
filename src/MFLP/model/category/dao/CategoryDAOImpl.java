package MFLP.model.category.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MFLP.model.dto.CategoryDTO;
import MFLP.model.util.DbUtil;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public int CategoryInsert(int no, String name) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "insert into BOOK_CATEGORY(CATEGORY_NO, CATEGORY_NAME) VALUES(?, ?)";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setString(2, name);

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(con, ps, null);
		}

		return result;
	}

	@Override
	public int CategoryUpdate(int no, String name) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;

		String sql = "UPDATE BOOK_CATEGORY SET CATEGORY_NO = ? , CATEGORY_NAME=? where CATEGORY_NO = ?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, no);
			ps.setString(2, name);
			ps.setInt(3, no);

			result = ps.executeUpdate();

		} finally {
			DbUtil.dbClose(con, ps, null);
		}
		return result;

	}

	@Override
	public int categoryDelete(int no) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete from BOOK_CATEGORY where CATEGORY_NO = ?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(con, ps, null);
		}
		return result;
	}

	@Override
	public List<CategoryDTO> categorySelectAll() throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CategoryDTO> list = new ArrayList<>();

		String sql = "select * from BOOK_CATEGORY";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setCategoryNo(rs.getInt(1));
				categoryDTO.setCategoryName(rs.getString(2));

				list.add(categoryDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return list;
	}

}
