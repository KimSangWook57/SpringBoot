package edu.pnu.service;

import java.util.List;

import edu.pnu.domain.MemberDAO;
import edu.pnu.domain.MemberVO;

//@Service
public class MemberService {

	private MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}

	public List<MemberVO> getMembers() {
		return memberDAO.getMembers();
	}

	public MemberVO getMember(int id) {
		return memberDAO.getMember(id);
	}

	public MemberVO addMembers(MemberVO member) {
		return memberDAO.addMembers(member);
	}

	public MemberVO updateMembers(MemberVO member) {
		return memberDAO.updateMembers(member);
	}

	public MemberVO removeMember(int id) {
		return memberDAO.removeMember(id);
	}

	

}
