package getDartData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DartDao {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/test";
	static final String USERNAME = "root";
	static final String PASSWORD = "0000";
	static final String TABLENAME = "dart_pa_full";

	public void insertdb(ArrayList<DartVo> db) {

		Connection conn = null;
		PreparedStatement stmt = null;

		DartVo dvv = null;

		for (int i = 0; i < db.size(); i++) {
			
			dvv = (DartVo) db.get(i);

			try {
				
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				
				String query = "INSERT INTO " + TABLENAME
						+ "(num, corp_code, corp_name, stock_code, corp_cls, report_nm, rcept_no, flr_nm, rcept_dt,rm) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				
				// 쿼리문을 전송을 위한 객체 생성.
				stmt = conn.prepareStatement(query); 
				stmt.setInt(1, 0);
				stmt.setString(2, dvv.getCorp_code());
				stmt.setString(3, dvv.getCorp_name());
				stmt.setString(4, dvv.getStock_code());
				stmt.setString(5, dvv.getCorp_cls());
				stmt.setString(6, dvv.getReport_nm());
				stmt.setString(7, dvv.getRcept_no());
				stmt.setString(8, dvv.getFlr_nm());
				stmt.setString(9, dvv.getRcept_dt());
				stmt.setString(10, dvv.getRm());
				// 쿼리문 실행.
				stmt.executeUpdate();
				// 접속종료.
				stmt.close();
				conn.close();
			} catch (ClassNotFoundException e) {
				System.out.println("Class Not Found Exection");
			} catch (SQLException e) {
				System.out.println("SQL Exception : " + e.getMessage());
			}
		}

	}

}