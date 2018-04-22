package example.com.persistencecontext;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import example.com.persistencecontext.domain.Member;

public class App6 {
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
			Member memberA = new Member();
			memberA.setId( "memberA" );
			memberA.setName( "회원A" );
			em.persist( memberA );			

			Member memberB = new Member();
			memberB.setId( "memberB" );
			memberB.setName( "회원B" );
			em.persist( memberB );
			
			TypedQuery<Member> query = em.createQuery( "select m from Member m", Member.class );
			List<Member> list = query.getResultList();
			
			for( Member member : list ) {
				System.out.println( member );
			}
			
		}catch(Exception e){
			tx.rollback();
		}
			
		// tx commit
		// 커밋하는 순간 데이터베이스에 INSERT SQL를 보낸다.
		tx.commit();					//[트랜잭션] 커밋
		
		
		
		
		em.close();
		emf.close();
    }
}