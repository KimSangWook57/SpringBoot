<<<<<<< HEAD
package edu.pnu.domain;

import java.sql.Connection;
// import java.util.Date;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	Connection con;
	// DB 연결
	public MemberDAO() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/springboot", "sa", "");
		} catch (Exception e) {
			System.out.println("db 접속 중 오류 발생.");
			e.printStackTrace();
		}

	}

	// 전체 멤버 표시
	public List<MemberVO> getMembers() {
		
		List<MemberVO> memberList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// query에 변수가 없다면, createStatement를 사용한다.
			String query = "select * from member";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				// 루프를 돌며 내용을 가져와야 한다.
				MemberVO memberVO = new MemberVO();
				int id = rs.getInt(1);
				String pass = rs.getString(2);
				String name = rs.getString(3);
				Date regidate = rs.getDate(4);
				// 만들어둔 memberVO에 내용을 넣는다.
				memberVO.setId(id);
				memberVO.setPass(pass);
				memberVO.setName(name);
				memberVO.setRegidate(regidate);
				// 내용을 리스트에 저장.
				memberList.add(memberVO);
			}
			return memberList;
			
		} catch (Exception e) {
			System.out.println("db 전체 검색 중 오류 발생");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				System.out.println("db 닫는 중 오류 발생");
				e.printStackTrace();
			}
		}
		return null;
	}

	// id로 검색하기
	public MemberVO getMember(int id) {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		MemberVO memberVO = new MemberVO();

		try {
			String query = "select * from member where id = ?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				// 검색된 내용이 있다면 정보를 가져와서
				int idx = rs.getInt(1);
				String pass = rs.getString(2);
				String name = rs.getString(3);
				Date regidate = rs.getDate(4);
				// memberVO에 넣어준 후 반환한다.
				memberVO.setId(idx);
				memberVO.setPass(pass);
				memberVO.setName(name);
				memberVO.setRegidate(regidate);
			}
			return memberVO;

		} catch (Exception e) {
			System.out.println("db 검색 중 오류 발생");
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
		return null;
	}
	// 마지막 자리를 구하는 코드.
	// id에 AI 속성을 넣었다면, 넣고 지울 때마다 id값이 계속 늘어나고 있기 때문에 쓰는 코드.
	private int getMaxId() {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			String query = "select max(id) from member";
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			// 커서 이동
			rs.next(); 
			// 커서의 id를 반환
			return rs.getInt(1);

		} catch (Exception e) {
			System.out.println("db 검색 중 오류 발생");
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
		return -1;
	
	}
	
	// 멤버 추가하기
	public MemberVO addMembers(MemberVO member) {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			String query = "insert into member(pass, name) values (?, ?);";
			psmt = con.prepareStatement(query);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			psmt.executeUpdate();

			// 쿼리문으로 데이터를 넣은 후에, 마지막 자리에 넣어진 member의 정보를 다시 들고 와야 한다.
			// getMaxId()를 이용해 마지막 자리를 구한다.
			return getMember(getMaxId());

		} catch (Exception e) {
			System.out.println("db 추가 중 오류 발생");
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
		return null;
	}
	// 멤버 갱신
	public MemberVO updateMembers(MemberVO member) {
		// 교수님 예제 수정본
//		PreparedStatement psmt = null;
//
//		try {
//			String query = "update member set ";
//			String append = "";
//			if (member.getName() != null) {
//				// name 문자열에서 오류가 발생했다.
//				// 이 코드는 문자열을 홑따옴표로 감싸주어야 했다. 
//				append = String.format("name = '%s'", member.getName());
//				query += append;
//			}
//			if (member.getPass() != null) {
//				if (append.isEmpty()) {
//					query += String.format("pass = %s", member.getPass());
//				} else {
//					query += ", " + String.format("pass = %s", member.getPass());
//				}
//			}
//			query += String.format(" where id = %s", member.getId());
//			System.out.println(query);
//			psmt = con.prepareStatement(query);
//			psmt.executeUpdate();
//
//			return getMember(member.getId());
		
		// 내 예제
		PreparedStatement psmt = null;
		try {
			// name만 갱신
			if (member.getPass() == null) {
				String query = "update member set name = ? where id = ?";
				psmt = con.prepareStatement(query);
				psmt.setString(1, member.getName());
				psmt.setInt(2, member.getId());
				System.out.println(query);
			}
			// pass만 갱신
			else if (member.getName() == null) {
				String query = "update member set pass = ? where id = ?";
				psmt = con.prepareStatement(query);
				psmt.setString(1, member.getPass());
				psmt.setInt(2, member.getId());
				System.out.println(query);
			}
			// 둘 다 갱신
			else {
				String query = "update member set pass = ?, name = ? where id = ?";
				psmt = con.prepareStatement(query);
				psmt.setString(1, member.getPass());
				psmt.setString(2, member.getName());
				psmt.setInt(3, member.getId());
				System.out.println(query);
			}
			psmt.executeUpdate();
			return getMember(member.getId());
		
		} catch (Exception e) {
			System.out.println("db 갱신 중 오류 발생");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
			} catch (Exception e) {
				System.out.println("db 닫는 중 오류 발생");
				e.printStackTrace();
			}
		}
		return null;
	}
	// 멤버 제거
	public MemberVO removeMember(int id) {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			MemberVO memberVO = getMember(id);

			String query = "delete from member where id = ?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();

			return memberVO;

		} catch (Exception e) {
			System.out.println("db 삭제 중 오류 발생");
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
		return null;
	}
}


=======
package edu.pnu.domain;

import java.sql.Connection;
// import java.util.Date;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	Connection con;
	// DB 연결
	public MemberDAO() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/springboot", "sa", "");
		} catch (Exception e) {
			System.out.println("db 접속 중 오류 발생.");
			e.printStackTrace();
		}

	}

	// 전체 멤버 표시
	public List<MemberVO> getMembers() {
		
		List<MemberVO> memberList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// query에 변수가 없다면, createStatement를 사용한다.
			String query = "select * from member";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				// 루프를 돌며 내용을 가져와야 한다.
				MemberVO memberVO = new MemberVO();
				int id = rs.getInt(1);
				String pass = rs.getString(2);
				String name = rs.getString(3);
				Date regidate = rs.getDate(4);
				// 만들어둔 memberVO에 내용을 넣는다.
				memberVO.setId(id);
				memberVO.setPass(pass);
				memberVO.setName(name);
				memberVO.setRegidate(regidate);
				// 내용을 리스트에 저장.
				memberList.add(memberVO);
			}
			return memberList;
			
		} catch (Exception e) {
			System.out.println("db 전체 검색 중 오류 발생");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				System.out.println("db 닫는 중 오류 발생");
				e.printStackTrace();
			}
		}
		return null;
	}

	// id로 검색하기
	public MemberVO getMember(int id) {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		MemberVO memberVO = new MemberVO();

		try {
			String query = "select * from member where id = ?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				// 검색된 내용이 있다면 정보를 가져와서
				int idx = rs.getInt(1);
				String pass = rs.getString(2);
				String name = rs.getString(3);
				Date regidate = rs.getDate(4);
				// memberVO에 넣어준 후 반환한다.
				memberVO.setId(idx);
				memberVO.setPass(pass);
				memberVO.setName(name);
				memberVO.setRegidate(regidate);
			}
			return memberVO;

		} catch (Exception e) {
			System.out.println("db 검색 중 오류 발생");
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
		return null;
	}
	// 마지막 자리를 구하는 코드.
	// id에 AI 속성을 넣었다면, 넣고 지울 때마다 id값이 계속 늘어나고 있기 때문에 쓰는 코드.
	private int getMaxId() {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			String query = "select max(id) from member";
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			// 커서 이동
			rs.next(); 
			// 커서의 id를 반환
			return rs.getInt(1);

		} catch (Exception e) {
			System.out.println("db 검색 중 오류 발생");
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
		return -1;
	
	}
	
	// 멤버 추가하기
	public MemberVO addMembers(MemberVO member) {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			String query = "insert into member(pass, name) values (?, ?);";
			psmt = con.prepareStatement(query);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			psmt.executeUpdate();

			// 쿼리문으로 데이터를 넣은 후에, 마지막 자리에 넣어진 member의 정보를 다시 들고 와야 한다.
			// getMaxId()를 이용해 마지막 자리를 구한다.
			return getMember(getMaxId());

		} catch (Exception e) {
			System.out.println("db 추가 중 오류 발생");
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
		return null;
	}
	// 멤버 갱신
	public MemberVO updateMembers(MemberVO member) {
		// 교수님 예제 수정본
//		PreparedStatement psmt = null;
//
//		try {
//			String query = "update member set ";
//			String append = "";
//			if (member.getName() != null) {
//				// name 문자열에서 오류가 발생했다.
//				// 이 코드는 문자열을 홑따옴표로 감싸주어야 했다. 
//				append = String.format("name = '%s'", member.getName());
//				query += append;
//			}
//			if (member.getPass() != null) {
//				if (append.isEmpty()) {
//					query += String.format("pass = %s", member.getPass());
//				} else {
//					query += ", " + String.format("pass = %s", member.getPass());
//				}
//			}
//			query += String.format(" where id = %s", member.getId());
//			System.out.println(query);
//			psmt = con.prepareStatement(query);
//			psmt.executeUpdate();
//
//			return getMember(member.getId());
		
		// 내 예제
		PreparedStatement psmt = null;
		try {
			// name만 갱신
			if (member.getPass() == null) {
				String query = "update member set name = ? where id = ?";
				psmt = con.prepareStatement(query);
				psmt.setString(1, member.getName());
				psmt.setInt(2, member.getId());
				System.out.println(query);
			}
			// pass만 갱신
			else if (member.getName() == null) {
				String query = "update member set pass = ? where id = ?";
				psmt = con.prepareStatement(query);
				psmt.setString(1, member.getPass());
				psmt.setInt(2, member.getId());
				System.out.println(query);
			}
			// 둘 다 갱신
			else {
				String query = "update member set pass = ?, name = ? where id = ?";
				psmt = con.prepareStatement(query);
				psmt.setString(1, member.getPass());
				psmt.setString(2, member.getName());
				psmt.setInt(3, member.getId());
				System.out.println(query);
			}
			psmt.executeUpdate();
			return getMember(member.getId());
		
		} catch (Exception e) {
			System.out.println("db 갱신 중 오류 발생");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
			} catch (Exception e) {
				System.out.println("db 닫는 중 오류 발생");
				e.printStackTrace();
			}
		}
		return null;
	}
	// 멤버 제거
	public MemberVO removeMember(int id) {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			MemberVO memberVO = getMember(id);

			String query = "delete from member where id = ?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();

			return memberVO;

		} catch (Exception e) {
			System.out.println("db 삭제 중 오류 발생");
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
		return null;
	}
}


>>>>>>> d313609aede0c0ae6a32d4149546e2d52435506d
