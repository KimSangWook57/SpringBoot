package edu.pnu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	private static final Logger Log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;

	public MemberController() {
		Log.info("MemberController 생성");
	}

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
