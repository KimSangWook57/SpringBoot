package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.MemberVO;

//@Service
public class MemberService {

	private List<MemberVO> list = new ArrayList<>();
	
	public MemberService() {
		// 인덱스 5의 ArrayList 생성 후 값 설정
		for (int i = 0; i < 5; i++) {
			list.add(new MemberVO(i, "1111", "디폴트", new Date()));
		}
		
	}

	// 전체 멤버 표시
	public List<MemberVO> getMembers() {
		return list;
	}
	
	// url의 입력값과 list의 id값이 같다면 그 list를 반환해주어야 한다.
	public MemberVO getMember(int id) {
		for (MemberVO idx : list) {
			if (id == idx.getId()) {
				return idx;
			}
		}
		return null;
	}
	
	// 멤버 추가하기
	public MemberVO addMembers(MemberVO member) {
		list.add(member);
		return member;
	}
	
	// 멤버 업데이트하기
	public MemberVO updateMembers(MemberVO member) {
		// 내 예제
//		for (MemberVO idx : list) {
//			if (member == idx) {
//				idx.setId(member.getId());
//				idx.setPass(member.getPass());
//				idx.setName(member.getName());
//				idx.setRegidate(member.getRegidate());
//				return member;
//			}
//		}
//		return null;
		
		// 교수님 예제
		MemberVO m = getMember(member.getId());
		if (m != null) {
<<<<<<< HEAD
			// m.setId(member.getId());
=======
			m.setId(member.getId());
>>>>>>> d313609aede0c0ae6a32d4149546e2d52435506d
			m.setPass(member.getPass());
			m.setName(member.getName());
			m.setRegidate(member.getRegidate());
			return member;
		}
		else {
			return null;
		}
		
	}
	// 멤버 제거하기.
	public MemberVO removeMember(int id) {
		for (MemberVO idx : list) {
			if (id == idx.getId()) {
				list.remove(idx);
				return idx;
			}
		}
		return null;
	}

}
