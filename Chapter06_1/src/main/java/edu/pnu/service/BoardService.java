package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.domain.BoardDAO;

@Service
public class BoardService {

	private BoardDAO boardDAO;

	public BoardService() {
		boardDAO = new BoardDAO();
	}

	public List<Board> getBoardList() {
		return boardDAO.getBoardList();
	}

	public Board getBoard(Long seq) {
		return boardDAO.getBoard(seq);
	}

}
