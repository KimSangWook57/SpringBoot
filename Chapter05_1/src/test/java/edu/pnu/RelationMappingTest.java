package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class RelationMappingTest {

	@Autowired
	private BoardRepository boardRepo;

	@Autowired
	private MemberRepository memberRepo;
	// 다대일 테스트(게시글 추가)
//	@Test
	public void testManyToOneInsert() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setPassword("member111");
		member1.setName("둘리");
		member1.setRole("User");
//		memberRepo.save(member1);

		Member member2 = new Member();
		member2.setId("member2");
		member2.setPassword("member222");
		member2.setName("도우너");
		member2.setRole("Admin");
//		memberRepo.save(member2);

		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle("둘리가 등록한 게시글 " + i);
			board.setContent("둘리가 등록한 게시글 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			// board를 매번 영속화할 필요가 없다.
//			boardRepo.save(board);
		}
		memberRepo.save(member1);

		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle("도우너가 등록한 게시글 " + i);
			board.setContent("도우너가 등록한 게시글 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
//			boardRepo.save(board);
		}
		memberRepo.save(member2);
	}
	// 다대일 테스트(게시글 상세조회)
//	@Test
	public void testManyToOneSelect() {
		Board board = boardRepo.findById(5L).get();
		System.out.println("[ " + board.getSeq() + "번 게시글 정보 ]");
		System.out.println("제목 : " + board.getTitle());
		System.out.println("내용 : " + board.getContent());
		// 작성자를 찾지 못햇을 때 오류가 나지 않게 하는 코드
		if (board.getMember() != null) {
			System.out.println("작성자 : " + board.getMember().getName());
			System.out.println("작성자 권한 : " + board.getMember().getRole());
		}
		else {
			System.out.println("작성자 없음");
		}
	}
	// 양방향 연관관계 테스트(회원이름의 게시물 전체조회)
	@Test
	public void testTwoWayMapping() {
		Member member = memberRepo.findById("member1").get();

		System.out.println("===============================");
		System.out.println(member.getName() + "이(가) 저장한 게시물 목록");
		System.out.println("===============================");
		List<Board> list = member.getBoardList();
		for (Board b : list) {
			System.out.println(b.toString());
		}

	}
	// 영속성 전이를 이용하여 삭제하기
//	@Test
	public void testCascadeDelete() {
		memberRepo.deleteById("member2");
	}

}
