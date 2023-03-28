package edu.pnu.service;

import java.sql.Connection;
// import java.util.Date;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDAO {

	Connection con;
	PreparedStatement st;
	ResultSet rs;

	public MemberDAO() {

		// DB 연결
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/springboot", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 전체 멤버 표시
	public List<MemberVO> getMembers() {

		List<MemberVO> memberList = new ArrayList<MemberVO>();
		MemberVO memberVO = new MemberVO();

		try {
			String query = "select * from member";
			st = con.prepareStatement(query);
			rs = st.executeQuery(query);

			while (rs.next()) {
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// url의 입력값과 list의 id값이 같다면 그 list를 반환해주어야 한다.
	public MemberVO getMember(int id) {

		MemberVO memberVO = new MemberVO();

		try {
			String query = "select * from member where id = ?";
			st = con.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery(query);

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
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	

//		// 멤버 추가하기
//		public MemberVO addMembers(MemberVO member) {
//			list.add(member);
//			return member;
//		}
//		
//		// 멤버 업데이트하기
//		public MemberVO updateMembers(MemberVO member) {
//			// 내 예제
////			for (MemberVO idx : list) {
////				if (member == idx) {
////					idx.setId(member.getId());
////					idx.setPass(member.getPass());
////					idx.setName(member.getName());
////					idx.setRegidate(member.getRegidate());
////					return member;
////				}
////			}
////			return null;
//			
//			// 교수님 예제
//			Statement st = null;
//			try {
//				String query = "update member set ";
//				String append = "";
//				if (member.getName() != null) {
//					append = String.format("name=%s", member.getName());
//					
//				}
//				if (member.getPass() != null) {
//					if (append.isEmpty()) {
//						query += String.format("pass=%s", member.getPass());
//					}
//					else {
//						query += append + "," + String.format("pass=%s", member.getPass());
//					}
//				}
//				query += String.format(" where id = %s", member.getId());
//				st = con.
//			}
//			
//			
//			
//			MemberVO m = getMember(member.getId());
//			if (m != null) {
//				// m.setId(member.getId());
//				m.setPass(member.getPass());
//				m.setName(member.getName());
//				m.setRegidate(member.getRegidate());
//				return member;
//			}
//			else {
//				return null;
//			}
//			
//		}
//		// 멤버 제거하기.
//		public MemberVO removeMember(int id) {
//			for (MemberVO idx : list) {
//				if (id == idx.getId()) {
//					list.remove(idx);
//					return idx;
//				}
//			}
//			return null;
//		}
//	
}
