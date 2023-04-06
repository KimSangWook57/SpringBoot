package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	public BoardRepository boardRepo;
	
//	@Test
//	public void BoardInsertTest() {
//		
//		Board b = new Board();
//		b.setTitle("title");
//		b.setWriter("writer");
//		b.setContent("content");
//		b.setCreateDate(new Date());
//		b.setCnt(0L);
//		
//		boardRepo.save(b);
//		
////		for (int i = 1; i < 11; i++) {
////			Board b = new Board();
////			b.setTitle("title" + i);
////			b.setWriter("writer");
////			b.setContent("content" + i);
////			b.setCreateDate(new Date());
////			b.setCnt(0L);
////			
////			boardRepo.save(b);
////		}
//	}
//	
//	@Test
//	public void testGetBoard() {
//		Board b = boardRepo.findById(1L).get();
//		System.out.println(b.toString());
//	}
//	
//	@Test
//	public void testUpdateBoard() {
//		System.out.println("=== 1번 게시글 조회 ===");
//		Board b = boardRepo.findById(1L).get();
//		
//		System.out.println("=== 1번 게시글 수정 ===");
//		b.setTitle("제목을 수정했습니다.");
//		boardRepo.save(b);
//		
//		System.out.println("=== 1번 게시글 조회 ===");
//		System.out.println(b.toString());
//	}
//	
//	@Test
//	public void testDeleteBoard() {
//		boardRepo.deleteById(2L);
//	}
//	
//	@Test
//	public void testFindByTitle() {
//		List<Board> boardlist = boardRepo.findByTitle("title10");
//		
//		System.out.println("검색 결과");
//		for (Board b : boardlist) {
//			System.out.println("---> " + b.toString());
//		}
//		
//	}
	
}
