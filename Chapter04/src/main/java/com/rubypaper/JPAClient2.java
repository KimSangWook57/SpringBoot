package com.rubypaper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Board1;

public class JPAClient2 {

	public static void main(String[] args) {
		// System.out.println("JPAClient");
		System.out.println("JPAClient");
		// EntityManagerFactory 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAClient");
		
		// 데이터 입력
		// insertBoard(emf);
		// id가 1인 데이터 출력
		// findBoardOne(emf, 1L);
		// 전체 데이터 출력(JPA Query) - JPA에서만 사용하는 쿼리
		// findBoardManyJPAQuery(emf);
		// 전체 데이터 출력(Native Query) - 일반적 쿼리
		// findBoardManyNativeQuery(emf);
		
		// id가 1인 데이터를 수정
		// updateBoard(emf, 1L);
		// 수정된 정보를 확인
		// findBoardOne(emf, 1L);
		
		// id가 2인 데이터를 삭제
		// deleteBoard(emf, 2L);
		// 수정된 정보를 확인
		// findBoardManyJPAQuery(emf);
		
		if(emf != null) {
			emf.close();
		}
				
	}

	private static void insertBoard(EntityManagerFactory emf) {
		// EntitiyManager 생성
		EntityManager em = emf.createEntityManager();
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();
		
		try {
			// Transaction 시작
			tx.begin();
			// 루프 10번 돌기
			for (int i = 1; i < 11; i++) {
				// 고의로 오류를 발생시키는 코드
//				if (i == 5) {
//					int a = 10 / 0;
//				}
				// 데이터 추가
//				Board board = new Board();
//				board.setTitle("JPA 제목");
//				board.setWriter("관리자");
//				board.setContent("JPA 글 등록 잘 되네요.");
//				board.setCreateDate(new Date());
//				board.setCnt(0L);
//				
//				// 글 등록
//				em.persist(board);
				
				// builder 방식
				Board1 board1 = Board1.builder()
									.title("title" + 1)
									.content("content" + 1)
									.writer("writer")
									.createDate(new Date())
									.build();
				
				// 글 등록
				em.persist(board1);				
				
			}
			
			// Transaction 커밋
			tx.commit();
			
		}
		
		
		catch (Exception e) {
			System.out.println("게시물 추가 중 오류 발생");
			e.printStackTrace();
			// 오류가 났다면 Transaction을 롤백한다.
			if (tx != null) {
				tx.rollback();
			}
		}
		finally {
			if(em != null) {
				em.close();
			}
			
		}
		
	}
	
	private static void findBoardOne(EntityManagerFactory emf, long seq) {
		// EntitiyManager 생성
		// find()는 트랜잭션이 필요하지 않다.
		EntityManager em = emf.createEntityManager();
		
		try {
			// 글 상세 조회
			Board searchBoard = em.find(Board.class, seq);
			System.out.println("---> " + searchBoard.toString());
		}
		catch (Exception e) {
			System.out.println("단일 게시물 조회 중 오류 발생");
			e.printStackTrace();
		}
		finally {
			if(em != null) {
				em.close();
			}
			
		}
		
		
		
	}

	private static void findBoardManyJPAQuery(EntityManagerFactory emf) {
		// EntitiyManager 생성
		EntityManager em = emf.createEntityManager();

		try {
			// 글 목록 조회
			String jpql = "select b from Board b order by b.seq desc";
			List<Board> boardList = em.createQuery(jpql, Board.class).getResultList();
			
			for (Board brd : boardList) {
				System.out.println("---> " + brd.toString());
			}
	
		}
		catch (Exception e) {
			System.out.println("게시물 전체 조회 중 오류 발생");
			e.printStackTrace();
		}
		finally {
			if(em != null) {
				em.close();
			}
			
		}
		
	}

	private static void findBoardManyNativeQuery(EntityManagerFactory emf) {
		// EntitiyManager 생성
		EntityManager em = emf.createEntityManager();
		
		try {
			// 글 목록 조회
			// case 1
			List<?> list1 = em.createNativeQuery("select * from Board", Board.class).getResultList();
			for (Object b : list1) {
				System.out.println("---> " + b.toString());
			}
			System.out.println("=========================================");
			// case 2
			List<Object[]> list2 = em.createNativeQuery("select * from Board").getResultList();
			for (Object[] b : list2) {
				for (int i = 0; i < b.length; i++) {
					if (i != 0) {
						System.out.print(",");
					}
					System.out.print(b[i]);
				}
				System.out.println();
			}
			
		}
		catch (Exception e) {
			System.out.println("게시물 전체 조회 중 오류 발생");
			e.printStackTrace();
		}
		finally {
			if(em != null) {
				em.close();
			}
			
		}
		
	}

	private static void updateBoard(EntityManagerFactory emf, long seq) {
		// EntitiyManager 생성
		EntityManager em = emf.createEntityManager();
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();
		
		try {
			// Transaction 시작
			tx.begin();
			
			// 수정할 게시글을 먼저 조회한 후 수정해야 한다.
			Board board = em.find(Board.class, seq);
			board.setTitle("검색한 게시글의 제목 수정");
			
			// Transaction 커밋
			tx.commit();
			
		}
		catch (Exception e) {
			System.out.println("게시물 수정 중 오류 발생");
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}		
		}
		finally {
			if(em != null) {
				em.close();
			}
			
		}
		
	}
	
	private static void deleteBoard(EntityManagerFactory emf, long seq) {
		// EntitiyManager 생성
		EntityManager em = emf.createEntityManager();
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();
		
		try {
			// Transaction 시작
			tx.begin();
			
			// 삭제할 게시물을 먼저 조회한 후 삭제해야 한다.
			Board board = em.find(Board.class, seq);
			board.setSeq(seq);
			
			// 게시글 삭제
			board.setSeq(seq);
			em.remove(board);
			
			// Transaction 커밋
			tx.commit();

		}
		catch (Exception e) {
			System.out.println("게시물 삭제 중 오류 발생");
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		finally {
			if(em != null) {
				em.close();
			}
			
		}
		
	}
	
	
	
	
	
}
