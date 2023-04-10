package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardservice;
	
	public BoardController() {
		
	}
	
	@GetMapping("/getBoardList")
	public List<Board> getBoardList() {
		return boardservice.getBoardList();
	}
	
	@GetMapping("/getBoardList/{seq}")
	public Board getBoard(@PathVariable Long seq) {
		return boardservice.getBoard(seq);
	}
	
}
