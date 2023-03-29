package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface {

	private List<MemberVO> list = new ArrayList<>();
	
	public MemberDaoListImpl() {
		for (int i = 0; i < 5; i++) {
			list.add(new MemberVO(i, "1111", "디폴트", new Date()));
		}
		
	}
	
	@Override
	public List<MemberVO> getMembers() {
		return list;
	}

	@Override
	public MemberVO getMember(int id) {
		for (MemberVO idx : list) {
			if (id == idx.getId()) {
				return idx;
			}
		}
		return null;
	}

	@Override
	public MemberVO addMembers(MemberVO member) {
		list.add(member);
		return member;
	}

	@Override
	public MemberVO updateMembers(MemberVO member) {
		MemberVO m = getMember(member.getId());
		if (m != null) {
			m.setPass(member.getPass());
			m.setName(member.getName());
			m.setRegidate(member.getRegidate());
			return member;
		}
		else {
			return null;
		}
	}

	@Override
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
