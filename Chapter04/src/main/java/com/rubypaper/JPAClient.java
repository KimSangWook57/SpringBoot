package com.rubypaper;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient {

	public static void main(String[] args) {
		
		// EntityManagerFactory 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		// EntitiyManager 생성
		EntityManager em = emf.createEntityManager();
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();
		
		try {
			// Transaction 시작
			tx.begin();
			
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요.");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			// 글 등록
			em.persist(board);
			
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
