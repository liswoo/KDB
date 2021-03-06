package getRcept_no;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;



public class DartDao {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";  // jdbc 드라이버 주소
	static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/test"; // DB 접속 주소
	static final String USERNAME = "root"; // DB ID
	static final String PASSWORD = "0000"; // DB Password
	static final String TABLENAME = "tb_rcept_num"; // DB Password

	private Connection conn = null;
	private Statement stmt = null;
	

	// DartVo객체를 입력받으면 객체안의 속성에 초기화된 데이터들을 데이터베이스에 인설트하는 메소드입니다.
	public void insertdb(ArrayList<DartVo> db) {
		
		DartVo dvv = null;
		String corp_code = null;
		String rcept_no = null;
		String area = null;
		
		for(int i =0; i < db.size(); i++) {
			dvv=(DartVo)db.get(i); //다운캐스팅. 어레이 리스트에 저장된 프로덕트 객체를 하나하나 가져와서
			corp_code=dvv.getCorp_code();
			rcept_no=dvv.getRcept_no();
			area = "4";
			
			
			String query = "INSERT INTO " + TABLENAME
					+ " VALUES(" +  "'" + corp_code + "','"+rcept_no +"','"+area + "'" + ");";
			
			try {
				//데이터베이스에 접속합니다.
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
				
				
				stmt = conn.createStatement(); // 쿼리문을 전송할 Statement 객체를 만듭니다.
				stmt.executeUpdate(query);// 쿼리문을 실행시킵니다.
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