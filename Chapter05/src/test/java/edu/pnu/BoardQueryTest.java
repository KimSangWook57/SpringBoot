package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class BoardQueryTest {

	@Autowired
	public BoardRepository boardRepo;

//	@Test
	public void BoardInsertTest() {
		for (int i = 1; i < 101; i++) {
			Board b = new Board();
			b.setTitle("title" + i);
			b.setWriter("writer");
			b.setContent("content" + i);
			b.setCreateDate(new Date());
			b.setCnt((long) (Math.random() * 101));

			boardRepo.save(b);
		}
	}

//	@Test
	public void testFindByTitleContaining() {
		List<Board> boardList = boardRepo.findByTitleContaining("1");
		
		System.out.println("검색 결과");
		for (Board b : boardList) {
			System.out.println("---> " + b.toString());
		}
		
	}

//	@Test
	public void testFindByTitleContainingAndGreaterThanCnt() {
		List<Board> boardList = boardRepo.findByTitleContainingAndCntGreaterThan("1", "50");

		System.out.println("검색 결과");
		for (Board b : boardList) {
			System.out.println("---> " + b.toString());
		}

	}

	@Test
	public void testFindByCntGreaterThanEqualAndLessThanEqualOrderBySeq() {
		// case 1
		// List<Board> boardList = boardRepo.findByCntGreaterThanEqualAndCntLessThanEqualOrderBySeq("10", "50");
		// case 2 (5개만 출력하도록 변경)
		Pageable paging = PageRequest.of(0, 5);
		List<Board> boardList = boardRepo.findByCntBetweenOrderBySeq("10", "50", paging);
		
		System.out.println("검색 결과");
		for (Board b : boardList) {
			System.out.println("---> " + b.toString());
		}		
		
	}
	
//	@Test
	public void testFindByTitleContainingOrContentContainingOrderByDesc() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContainingOrderBySeqDesc("10", "2");
		
		System.out.println("검색 결과");
		for (Board b : boardList) {
			System.out.println("---> " + b.toString());
		}		
		
	}
	
}
