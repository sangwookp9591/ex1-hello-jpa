package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //EntityManagerFactory 를 만드는 순간 DB연결등 왠만한게 다 되어있다.
        //EntityManagerFactory는 application loading시점에 딱 하나만 만들어 와야한다.

        EntityManager em = emf.createEntityManager();
        //여기서 entityManager를 꺼내고 여기서 db에 데이터를 저장하고 불러온다든가 하는 code를 정의한다.
        /*그리고 실제 tranction 단위( db저장하거나 하는) db conneciton 얻어서 쿼리를 날리고 종료되는 하나의 일련의 과정이 수행될떄마다
        EntityManager를 만들어줘야한다.*/
        //code

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");
        //Q:이렇게까지 했는데도 error가 난다 왜?
        //A:JPA에서는 꼭 트랜젝션이라는 단위가 중요하다.(모든 데이터를 변경하는 모든작업은 traction안에서 작업해야한다.)  tx.begin(); tx.commit
        em.persist(member);

        tx.commit();

        em.close();;

        emf.close();;
    }
}
