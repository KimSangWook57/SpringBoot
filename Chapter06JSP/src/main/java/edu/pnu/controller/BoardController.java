package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@Controller
//@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
//	@RequestMapping(value = "/getBoardList", method = RequestMethod.GET)
	// 전체글 리스트 열람
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model, Board board) {
		List<Board> boardList = boardService.getBoardList(board);
		
		model.addAttribute("boardList", boardList);
		// view 이름을 return하게 한다.
		return "getBoardList";
	}
	// 글쓰기를 위한 창 생성
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "insertBoard";
	}
	// 글쓰기 버튼을 누르면 글 등록
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		boardService.insertBoard(board);
		// 작성한 뒤 글 목록 리스트로 돌아간다.
		return "redirect:getBoardList";
	}
	// 글 내용 상세보기
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "getBoard";
	}
	// 글 내용 수정
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		// 수정한 뒤 글 목록 리스트로 간다.
		return "forward:getBoardList";
	}
	// 글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		// 삭제한 뒤 글 목록 리스트로 간다.
		return "forward:getBoardList";
	}
	
	
}
