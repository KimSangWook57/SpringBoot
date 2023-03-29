package edu.pnu.dao;

import java.util.List;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {

	// 전체 멤버 표시
	List<MemberVO> getMembers();

	// id로 검색하기
	MemberVO getMember(int id);

	// 멤버 추가하기
	MemberVO addMembers(MemberVO member);

	// 멤버 갱신
	MemberVO updateMembers(MemberVO member);

	// 멤버 제거
	MemberVO removeMember(int id);

}