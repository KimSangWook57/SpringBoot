package edu.pnu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	
	
	MemberService memberService = new MemberService();
	
	private static final Logger Log = LoggerFactory.getLogger(MemberController.class);
	
	public MemberController() {
		// 로그 테스트
		Log.error("이것은 error 메시지입니다.");
		Log.warn("이것은 warn 메시지입니다.");
		Log.info("이것은 info 메시지입니다."); // 디폴트, 이 밑으로는 나오지 않는다.
		Log.debug("이것은 debug 메시지입니다."); 
		Log.trace("이것은 trace 메시지입니다.");
	}
	// 서버를 어떤 형식으로 구동할 것인지는 type을 받아서 정한다.
	@GetMapping("/member")
	public List<MemberVO> getMembers(String type) {
		System.out.println("getMembers");
		return memberService.getMembers(type);
	}
	@GetMapping("/member/{id}")
	public MemberVO getMember(String type, @PathVariable Integer id) {
		System.out.println("getMember");
		return memberService.getMember(type, id);
	}
	@PostMapping("/member")
	public MemberVO addMember(String type, MemberVO member) {
		System.out.println("addMember");
		System.out.println("member : " + member);
		return memberService.addMembers(type, member);
	}
	@PutMapping("/member")
	public MemberVO updateMembers(String type, MemberVO member) {
		System.out.println("updateMembers");
		System.out.println("member : " + member);
		return memberService.updateMembers(type, member);
		
	}
	@DeleteMapping("/member/{id}")
	public MemberVO removeMember(String type, @PathVariable Integer id) {
		System.out.println("removeMember");
		return memberService.removeMember(type, id);
		
	}
	
	
}
