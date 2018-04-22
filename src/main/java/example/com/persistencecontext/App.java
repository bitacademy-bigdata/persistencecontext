package example.com.persistencecontext;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import example.com.persistencecontext.domain.Member;

public class App {
	public static void main( String[] args ){
		
		// manager factory 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabookmall"); 
        // manager 생성
		EntityManager em = emf.createEntityManager();
		// get transaction
		EntityTransaction tx = em.getTransaction();
		// begin transaction
		tx.begin();
		
		try{
			// business logic
			
			// testInsert( em );
			// testFind01( em );
			// testFind02( em );
			testIdentity( em );

		}catch(Exception e){
			tx.rollback();
		}
			
		// tx commit
		tx.commit();
		
		
		em.close();
		emf.close();
    }
	
	public static void testInsert( EntityManager em ) {
		Member member = new Member();
		
		member.setId( "member1" );
		member.setName( "회원1" );
		
		em.persist( member );
	}
	

	public static void testFind01( EntityManager em ) {
		Member member = new Member();
		
		member.setId( "member1" );
		member.setName( "회원1" );

		// 1차 캐시에 저장됨
		em.persist( member );
		
		// 1차 캐시에서 조회
		Member findMember = em.find( Member.class, "member1" );
		System.out.println( findMember );
	}
	
	public static void testFind02( EntityManager em ) {
		//  DB에서 조회
		Member findMember = em.find( Member.class, "member1" );
		System.out.println( findMember );
	}
	
	public static void testIdentity( EntityManager em ) {
		Member a = em.find( Member.class, "member1" );
		Member b = em.find( Member.class, "member1" );
		
		System.out.println( a == b );
	}
}