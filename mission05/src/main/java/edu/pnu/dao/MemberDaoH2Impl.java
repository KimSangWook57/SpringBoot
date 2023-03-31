package edu.pnu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoH2Impl implements MemberInterface {
	
	Connection con;

	// DB 연결
	public MemberDaoH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/springboot", "sa", "");
		} catch (Exception e) {
			System.out.println("db 접속 중 오류 발생.");
			e.printStackTrace();
		}

	}
	
	@Override
	public List<MemberVO> getMembers() {
		
		List<MemberVO> memberList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = "select * from member";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				MemberVO memberVO = new MemberVO();
				int id = rs.getInt(1);
				String pass = rs.getString(2);
				String name = rs.getString(3);
				Date regidate = rs.getDate(4);
				
				memberVO.setId(id);
				memberVO.setPass(pass);
				memberVO.setName(name);
				memberVO.setRegidate(regidate);
				
				memberList.add(memberVO);
				
			}
			
			return memberList;
		} catch(Exception e) {
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

	@Override
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
				int idx = rs.getInt(1);
				String pass = rs.getString(2);
				String name = rs.getString(3);
				Date regidate = rs.getDate(4);
				
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

	@Override
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
	
	@Override
	public MemberVO updateMembers(MemberVO member) {
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

	@Override
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


