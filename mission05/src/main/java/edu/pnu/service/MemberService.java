package edu.pnu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.LogDaoH2Impl;
import edu.pnu.dao.MemberDaoH2Impl;
import edu.pnu.dao.MemberDaoListImpl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

	// map을 이용해 어떤 데이터베이스를 사용할지 정하는 코드
	private Map<String, MemberInterface> map;
	// 로그를 찍기 위해 정보를 보내는 코드
	@Autowired
	private LogDaoH2Impl logDao;

	// 사용할 DB들을 담는 해쉬맵
	public MemberService() {
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

	// 로그에 찍을 내용을 전달해주기 위해, 받은 정보를 list라는 변수에 담은 뒤, 이 정보를 넘겨준다.
	public List<MemberVO> getMembers(String type) {
		List<MemberVO> list = getDAO(type).getMembers();
		logDao.addLog("get", type, list);
		return list;
	}

	public MemberVO getMember(String type, int id) {
		MemberVO m = getDAO(type).getMember(id);
		logDao.addLog("get", type, m);
		return m;
	}

	public MemberVO addMembers(String type, MemberVO member) {
		MemberVO m = getDAO(type).addMembers(member);
		logDao.addLog("post", type, m);
		return m;
	}

	public MemberVO updateMembers(String type, MemberVO member) {
		MemberVO m = getDAO(type).updateMembers(member);
		logDao.addLog("put", type, m);
		return m;
	}

	public MemberVO removeMember(String type, int id) {
		MemberVO m = getDAO(type).removeMember(id);
		logDao.addLog("delete", type, m);
		return m;
	}

}
