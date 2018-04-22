package example.com.persistencecontext;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import example.com.persistencecontext.domain.Member;

public class App3 {
	public static void main( String[] args ){
		
		// manager factory 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabookmall"); 
        // manager 생성
		EntityManager em = emf.createEntityManager();
		// get transaction
		EntityTransaction tx = em.getTransaction();
		
		
		// begin transaction
		// 엔티티 매니저는 데이터 변경 시 트랜잭션을 시작해야 한다.
		tx.begin();						//[트랜잭션] 시작
		
		try{

			// 삭제 대상 엔티티 조회
			Member memberA = em.find( Member.class, "memberA" );
			
			// 엔티티 삭제
			em.remove( memberA );
			
		}catch(Exception e){
			tx.rollback();
		}
			
		// tx commit
		// 커밋하는 순간 데이터베이스에 delete SQL를 보낸다.
		tx.commit();					//[트랜잭션] 커밋
		
		
		
		
		em.close();
		emf.close();
    }
}