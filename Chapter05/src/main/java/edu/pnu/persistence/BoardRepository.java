package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
	// 특정 제목을 가진 게시물 검색
	// List<Board> findByTitle(String title);
	
	// title에 '1'이 포함되는 데이터 출력
	List<Board> findByTitleContaining(String keyword);
	
	// title에 '1'이 포함되고 cnt가 50보다 큰 데이터 출력
	List<Board> findByTitleContainingAndCntGreaterThan(String title, String cnt);
	
	// cnt가 10~50 사이인 데이터를 seq 오름차순으로 정렬
	// case 1
	List<Board> findByCntGreaterThanEqualAndCntLessThanEqualOrderBySeq(String cnt1, String cnt2);
	// case 2 (페이징 처리, between 사용)
	List<Board> findByCntBetweenOrderBySeq(String cnt1, String cnt2, Pageable paging);
	
	// title에 '10'이 포함되거나 content에 '2'가 포함되는 데이터를 seq 내림차순으로 정렬
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content);
	
	// 위치 기반 쿼리문(+ 특정 변수만 보기) 사용예시.
	@Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest01(String searchKeyword);
	
	// 이름 기반 쿼리문 사용예시.
	@Query("SELECT b FROM Board b " + "WHERE b.title like %:searchKeyword% " + "ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest02(@Param("searchKeyword") String searchKeyword);
	
	// 네이티브 쿼리문도 사용 가능.
	@Query(value = "select seq, title, writer, create_date " 
				+ "from board where title like '%'||?1||'%' "
				+ "order by seq desc", nativeQuery = true)
	List<Object[]> queryAnnotationTest03(String searchKeyword);
	
	
}
