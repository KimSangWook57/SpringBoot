package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepo;
	
	// boardRepo의 모든 내용을 findAll()로 가져온다.
	@Override
	public List<Board> getBoardList(Board board) {
		return (List<Board>) boardRepo.findAll();
	}
	
	// boardRepo의 특정 내용을 findById(board.getSeq()).get - 보드의 seq를 이용해 가져온다.
	@Override
	public Board getBoard(Board board) {
		return boardRepo.findById(board.getSeq()).get();
	}
	
	// boardRepo에 board의 내용을 save해서 집어넣는다.
	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
		
	}
	
	// 수정할 board를 찾고, 내용과 제목을 바꾼 값으로 변경한 뒤에 boardRepo의 save로 집어넣는다.
	@Override
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		
		boardRepo.save(findBoard);
	}
	
	// boardRepo의 특정 내용을 deleteById(board.getSeq())를 사용해 가져와 삭제한다.
	@Override
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());
		
	}


	
}
