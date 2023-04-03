package com.rubypaper;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient2 {

	public static void main(String[] args) {
		System.out.println("JPAClient");
		// EntityManagerFactory 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAClient");
		
		// int a = 10 / 0;
		
		// 데이터 입력
		insertBoard(emf);
		// id가 1인 데이터 출력
		// findBoardOne(emf, 1);
		// 전체 데이터 출력(JPA Query)
		// findBoardManyJPAQuery(emf);
		// 전체 데이터 출력(Native Query)
		// findBoardManyNativeQuery(emf);
		
		// id가 1인 데이터를 수정
		// updateBoard(emf, 1);
		// 수정된 정보를 확인
		// findBoardOne(emf, 1);
		
		// id가 2인 데이터를 삭제
		// deleteBoard(emf, 2);
		// 수정된 정보를 확인
		// findBoardManyJPAQuery(emf);
				
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
				
				Board board = new Board();
				board.setTitle("JPA 제목");
				board.setWriter("관리자");
				board.setContent("JPA 글 등록 잘 되네요.");
				board.setCreateDate(new Date());
				board.setCnt(0L);
				
				// 글 등록
				em.persist(board);
			}
			
			// Transaction 커밋
			tx.commit();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			// 오류가 났다면 Transaction을 롤백한다.
			tx.rollback();
		}
		finally {
			if(em != null) {
				em.close();
			}
			if(emf != null) {
				emf.close();
			}
		}
		
	}

}
