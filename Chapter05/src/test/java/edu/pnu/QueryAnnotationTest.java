package edu.pnu;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class QueryAnnotationTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
//	@Test
	public void testQueryAnnotationTest01() {
		List<Board> boardList = boardRepo.queryAnnotationTest01("title10");
		
		System.out.println("검색 결과");
		for (Board b : boardList) {
			System.out.println("---> " + b.toString());
		}
		
	}
	
//	@Test
	public void testQueryAnnotationTest02() {
		List<Board> boardList = boardRepo.queryAnnotationTest02("title10");
		
		System.out.println("검색 결과");
		for (Board b : boardList) {
			System.out.println("---> " + b.toString());
		}
		
	}
	
//	@Test
	public void testQueryAnnotationTest03() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest03("title10");
		
		System.out.println("검색 결과");
		for (Object[] row : boardList) {
			System.out.println("---> " + Arrays.toString(row));
		}
		
	}
	
}
