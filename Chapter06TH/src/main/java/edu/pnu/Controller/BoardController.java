package edu.pnu.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.service.BoardService;

@SessionAttributes("member")
@Controller // view를 return
// @RestController // data를 return
public class BoardController {

	@Autowired
	BoardService boardservice;
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	// 테스트 코드
	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("greeting", "Hi thymeleaf~~");
		return "hello";
	}

	// 전체 리스트 보기(Controller)
	@GetMapping("/getBoardList")
	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		List<Board> boardList = boardservice.getBoardList(board);
		model.addAttribute("boardList", boardList);
		// view 이름
		return "getBoardList";
	}

	// 특정 게시글 내용 상세보기
	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Model model, Board board) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		model.addAttribute("board", boardservice.getBoard(board));
		return "getBoard";
	}

	// 글쓰기 창 띄우기
	@GetMapping("/insertBoard")
	public String insertBoardView(@ModelAttribute("member") Member member) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		return "insertBoard";
	}

	// 게시글 추가
	@PostMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		boardservice.insertBoard(board);
		// 작성한 뒤 글 목록 리스트로 돌아간다.
		return "redirect:getBoardList";
	}

	// 게시글 수정
	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		boardservice.updateBoard(board);
		// 수정한 뒤 글 목록 리스트로 간다.
		// forward를 쓰면 데이터 중복으로 405 오류가 발생하므로, 리다이렉트로 바꾸어 주었다.
		return "redirect:getBoardList";
	}

	// 게시글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		boardservice.deleteBoard(board);
		// 삭제한 뒤 글 목록 리스트로 간다.
		// forward를 쓰면 데이터 중복으로 405 오류가 발생하므로, 리다이렉트로 바꾸어 주었다.
		return "redirect:getBoardList";
	}

}
