package orderhere.order.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	public static Connection conn;
	public static Statement stmt;

	public static void init() 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:XE",
						//127.0.0.1이 로컬호스트를 의미.
						// 오라클 프로그램에 연결하기 위한 프로토콜
					"allnight", 
					"orderhere");
			stmt = conn.createStatement();//문장을 받을수 있는 객체 생성
			
			System.out.println("OK!");
			
		} catch (ClassNotFoundException e) {
			System.out.println("예외 발생 : 해당 드라이버가 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("예외 발생 : 접속 정보 확인이 필요합니다.");
			e.printStackTrace();
		}
	}
	//조회용
	public static ResultSet getResult(String sql) 
	{
		try {
			Statement stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//수정용
	public static void executeSQL(String sql) 
	{
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeConn(Connection conn, Statement stmt) 
	{
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("닫을 객체가 없습니다.");
			e.printStackTrace();
		}
	}
}
