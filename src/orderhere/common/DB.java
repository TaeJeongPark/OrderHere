package orderhere.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DB {
	
	public static Connection conn;
	public static Statement stmt;

	public static void init() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:XE", 
					"allnight", 
					"orderhere");
			
			stmt = conn.createStatement();
			
			System.out.println("DB연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("예외 발생 : 해당 드라이버가 없습니다.");
			e.printStackTrace();
			connFailAlert();
		} catch (SQLException e) {
			System.out.println("예외 발생 : 접속 정보 확인이 필요합니다.");
			e.printStackTrace();
			connFailAlert();
		}
		
	}

	//조회용
	public static ResultSet getResult(String sql) {
		try {
			stmt = conn.createStatement();
			System.out.println("Statement 객체 생성 성공");
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			connFailAlert();
			return null;
		}
	}
	
	//수정용
	public static void executeSQL(String sql) {
		try {
			stmt = conn.createStatement();
			System.out.println("Statement 객체 생성 성공");
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			connFailAlert();
			e.printStackTrace();
		}
	}
	
	//오류 메시지 출력 Alert
	private static void connFailAlert() {
		JOptionPane.showMessageDialog(null, "접속에 실패했습니다.\n다시 시도해주세요.", "접속 실패", JOptionPane.ERROR_MESSAGE);
	}
	
}
