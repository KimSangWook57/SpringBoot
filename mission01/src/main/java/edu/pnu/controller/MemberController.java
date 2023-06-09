package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public MemberController() {
		
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
