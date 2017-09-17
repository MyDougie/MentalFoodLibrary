package MFLP.model.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil {
	static DataSource ds;
	//로드
		static {
			try {
				Context context = new InitialContext();
				ds = (DataSource)context.lookup("java:/comp/env/jdbc/myoracle");//lookup DB를 관리해주는 객체
				
			} catch (NamingException e) {
			e.printStackTrace();
			}
		}
	//연결
	
		
	public static Connection getConnection()throws SQLException{

		return ds.getConnection();
	}
	//닫기
	public static void dbClose(Connection con, Statement stmt, ResultSet rs){
		
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(con!=null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
}
