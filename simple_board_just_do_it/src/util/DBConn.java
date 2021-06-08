package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 커넥션 객체
			conn = DriverManager.getConnection("jdbc:oracle:thin:@human.lepelaka.net:1521/orcl", "BOOK_EX01", "BOOK_EX01");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
