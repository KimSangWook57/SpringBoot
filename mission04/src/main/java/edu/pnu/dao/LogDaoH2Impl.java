package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LogDaoH2Impl {
	
	Connection con;
	// DB 연결
	public LogDaoH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/springboot", "sa", "");
		} catch (Exception e) {
			System.out.println("db 접속 중 오류 발생.");
			e.printStackTrace();
		}

	}
	// 로그를 dblog 테이블에 추가하는 코드.
	public void addLog(String string, String type, Object obj) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			String query = "insert into dblog(string, type, success) values (?, ?, ?);";
			psmt = con.prepareStatement(query);
			psmt.setString(1, string);
			psmt.setString(2, type);
			// obj가 null이 아니라면 성공, 아니면 실패.
			if (obj == null) {
				psmt.setBoolean(3, false);
			}
			else {
				psmt.setBoolean(3, true);
			}
			
			psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("db 로그 추가 중 오류 발생");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
			} catch (Exception e) {
				System.out.println("db 닫는 중 오류 발생");
				e.printStackTrace();
			}
		}
		// return null;
	}
	

}
