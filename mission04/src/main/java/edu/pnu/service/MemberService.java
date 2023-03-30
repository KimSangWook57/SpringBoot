package edu.pnu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDaoH2Impl;
import edu.pnu.dao.MemberDaoH2Impl;
import edu.pnu.dao.MemberDaoListImpl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {

	// map을 이용해 어떤 데이터베이스를 사용할지 정하는 코드 
	private Map<String, MemberInterface> map;
	// 로그를 찍기 위해 정보를 보내는 코드
	private LogDaoH2Impl logDao;
	
	// 사용할 DB들을 담는 해쉬맵
	public MemberService () {
		map = new HashMap<>();
		map.put("h2", new MemberDaoH2Impl());
		map.put("list", new MemberDaoListImpl());
		
		logDao = new LogDaoH2Impl();
	}
	
	// type에 따라 사용할 DB 형식을 가져오는 코드
	// getDAO(type)에 따라, 정해진 DB에 맞게 호출될 것이다.
	public MemberInterface getDAO(String type) {
		return map.get(type);
	}
	
	public List<MemberVO> getMembers(String type) {
		List<MemberVO> list = getDAO(type).getMembers();
		logDao.addLog("get", type, list);
		
		return list;
		// return getDAO(type).getMembers();
	}

	public MemberVO getMember(String type, int id) {
		MemberVO m = getDAO(type).getMember(id);
		logDao.addLog("get", type, m);
		
		return m;
		// return getDAO(type).getMember(id);
	}

	public MemberVO addMembers(String type, MemberVO member) {
		MemberVO m = getDAO(type).addMembers(member);
		logDao.addLog("post", type, m);
		
		return m;
		// return getDAO(type).addMembers(member);
	}

	public MemberVO updateMembers(String type, MemberVO member) {
		MemberVO m = getDAO(type).updateMembers(member);
		logDao.addLog("put", type, m);
		
		return m;
		// return getDAO(type).updateMembers(member);
	}

	public MemberVO removeMember(String type, int id) {
		MemberVO m = getDAO(type).removeMember(id);
		logDao.addLog("delete", type, m);
		
		return m;
		// return getDAO(type).removeMember(id);
	}

}
