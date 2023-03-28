<<<<<<< HEAD
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {
	
//	@Autowired
	MemberService memberService = new MemberService();
	
	private static final Logger Log = LoggerFactory.getLogger(MemberController.class);
	
	public MemberController() {
		
		Log.error("이것은 error 메시지입니다.");
		Log.warn("이것은 warn 메시지입니다.");
		Log.info("이것은 info 메시지입니다."); // 디폴트, 이 밑으로는 나오지 않는다.
		Log.debug("이것은 debug 메시지입니다."); 
		Log.trace("이것은 trace 메시지입니다.");
	}
	
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		System.out.println("getMembers");
		return memberService.getMembers();
	}
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		System.out.println("getMember");
		return memberService.getMember(id);
	}
	@PostMapping("/member")
	public MemberVO addMember(MemberVO member) {
		System.out.println("addMember");
		System.out.println("member : " + member);
		return memberService.addMembers(member);
	}
	@PutMapping("/member")
	public MemberVO updateMembers(MemberVO member) {
		System.out.println("updateMembers");
		System.out.println("member : " + member);
		return memberService.updateMembers(member);
		
	}
	@DeleteMapping("/member/{id}")
	public MemberVO removeMember(@PathVariable Integer id) {
		System.out.println("removeMember");
		return memberService.removeMember(id);
		
	}
	
	
}
=======
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {
	
//	@Autowired
	MemberService memberService = new MemberService();
	
	private static final Logger Log = LoggerFactory.getLogger(MemberController.class);
	
	public MemberController() {
		
		Log.error("이것은 error 메시지입니다.");
		Log.warn("이것은 warn 메시지입니다.");
		Log.info("이것은 info 메시지입니다."); // 디폴트, 이 밑으로는 나오지 않는다.
		Log.debug("이것은 debug 메시지입니다."); 
		Log.trace("이것은 trace 메시지입니다.");
	}
	
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		System.out.println("getMembers");
		return memberService.getMembers();
	}
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		System.out.println("getMember");
		return memberService.getMember(id);
	}
	@PostMapping("/member")
	public MemberVO addMember(MemberVO member) {
		System.out.println("addMember");
		System.out.println("member : " + member);
		return memberService.addMembers(member);
	}
	@PutMapping("/member")
	public MemberVO updateMembers(MemberVO member) {
		System.out.println("updateMembers");
		System.out.println("member : " + member);
		return memberService.updateMembers(member);
		
	}
	@DeleteMapping("/member/{id}")
	public MemberVO removeMember(@PathVariable Integer id) {
		System.out.println("removeMember");
		return memberService.removeMember(id);
		
	}
	
	
}
>>>>>>> d313609aede0c0ae6a32d4149546e2d52435506d
