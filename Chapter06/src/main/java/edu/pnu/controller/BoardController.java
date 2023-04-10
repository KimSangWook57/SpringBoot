package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.DBoard;
import edu.pnu.service.BoardService;

// @RestController와 @Controller의 차이?
@RestController
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/board")
//	public List<DBoard> getBoardList(Model model) {
	public void getBoardList(Model model) {
		List<DBoard> list = boardService.getBoardList();
		model.addAttribute("boardList", list);
//		return boardService.getBoardList();
	}
	
//	@GetMapping("/board")
//	public List<DBoard> getBoard(Long seq) {
//		if (seq == null) {
//			System.out.println("getBoard : All");
//			return boardService.getBoardList();
//		}
//		
//		System.out.println("getBoard : " + seq);
//		List<DBoard> list = new ArrayList<>();
//		list.add(boardService.getBoard(seq));
//		
//		return list;
//	}
	
	@GetMapping("/board/{seq}")
	public DBoard getBoardByPath(@PathVariable Long seq) {
		System.out.println("getBoard : " + seq);
		return boardService.getBoard(seq);
	}
	
}
