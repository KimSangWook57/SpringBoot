package edu.pnu.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@Controller // view를 return
// @RestController // data를 return
public class BoardController {

	@Autowired
	BoardService boardservice;

	// 테스트 코드
	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("greeting", "Hi thymeleaf~~");
		return "hello";
	}

	// 전체 리스트 보기(Controller)
	@GetMapping("/getBoardList")
	public String getBoardList(Model model, Board board) {
		List<Board> boardList = boardservice.getBoardList(board);
		model.addAttribute("boardList", boardList);
		// view 이름
		return "getBoardList";
	}

	// 특정 게시글 내용 상세보기
	@GetMapping("/getBoardList/{seq}")
	public String getBoard(Model model, Board board) {
		model.addAttribute("board", boardservice.getBoard(board));
		return "getBoard";
	}

	// 글쓰기 창 띄우기
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "insertBoard";
	}

	// 게시글 추가
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		boardservice.insertBoard(board);
		// 작성한 뒤 글 목록 리스트로 돌아간다.
		return "redirect:getBoardList";
	}

	// 게시글 수정
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardservice.updateBoard(board);
		// 수정한 뒤 글 목록 리스트로 간다.
		return "forward:getBoardList";
	}

	// 게시글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardservice.deleteBoard(board);
		// 삭제한 뒤 글 목록 리스트로 간다.
		return "forward:getBoardList";
	}

//	// 전체 리스트 보기(RestController)
//	@GetMapping("/getBoardList")
//	public List<TBoard> getBoardList(TBoard board) {
//		return boardservice.getBoardList(board);
//	}

}
