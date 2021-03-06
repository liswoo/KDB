package downloadFiles_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exquery {
	
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";  
	static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/test"; 
	static final String USERNAME = "root"; 
	static final String PASSWORD = "0000"; 
	static final String TABLENAME = "dart_pa_detail";
	// ex) 연결 처리 객체 만들기.
	// 1. 공통 필드 선언
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	String test = null;	
	// 2. 공통 메서드 선언
	public void setConn() {
		// 1) 드라이버 연결
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("오류:"+e.getMessage());
		}
		
		try {
			con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("오류:"+e.getMessage());
		}
		
//		System.out.println("접속 성공");
	}
	// 조회 처리 메서드 구현 1단계(출력)
	public String exquery(int i) {
		// 1. 연결공통메서드 호출
		
		try {
			setConn();
			// 2. Statement 객체 생성 (Connection ==> Statement)
			stmt = con.createStatement();
			String sql = "select * from dart_pa_detail where num="+i;
			// 3. ResultSet 객체 생성.sql의 결과
			rs = stmt.executeQuery(sql);
			// 4. while()을 통해 결과 내용 처리 sql의 결과는 처리
			// rs.next() 행단위로 이동하게 처리..
			
			while(rs.next()) {
				// rs.get데이터유형("컬럼명/alias명");
//				System.out.println(rs.getString("rcept_no")+"\t");
				test = rs.getString("rcept_no");
				
			}
			
			// 5. 자원 해제
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// 6. 예외 처리..
			System.out.println("오류:"+e.getMessage());
			if(rs!=null) rs = null; // 강제로 자원해제..
			if(stmt!=null) stmt = null; // 강제로 자원해제..
			
		}
		return test;
		
	} 
	
	// ex) A03_DatabaseDao.java를 만들고,
	//		필드, 공통메서드, public void deptList()로 선언하여
	//		sql을 select * from dept로 처리된 행을 출력하는 내용까지 처리.
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 객체 생성 및 메서드 처리..
		Exquery dao = new Exquery();
		DartDao dart = new DartDao();
		
		for(int i=290001; i<=300000; i++) {
			try {
				
				dart.downloadfiles(dao.exquery(i));
				if(i%100==0) {
					Thread.sleep(6000);
				}
			}catch(InterruptedException e){
				 e.printStackTrace();
			}
			
		}
		
	}

}