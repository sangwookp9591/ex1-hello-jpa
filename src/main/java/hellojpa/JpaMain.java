package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //EntityManagerFactory 를 만드는 순간 DB연결등 왠만한게 다 되어있다.
        //EntityManagerFactory는 application loading시점에 딱 하나만 만들어 와야한다.

        EntityManager em = emf.createEntityManager();
        //여기서 entityManager를 꺼내고 여기서 db에 데이터를 저장하고 불러온다든가 하는 code를 정의한다.
        /*그리고 실제 tranction 단위( db저장하거나 하는) db conneciton 얻어서 쿼리를 날리고 종료되는
        하나의 일련의 과정(고객의 요청이 올떄마다)이 수행될떄마다 EntityManager를 만들어줘야한다.*/
        //em은 쓰레드간에 공유 절대 하면 안되고 사용하고 버려야한다.



        //code

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //JPA의 모든 데이터 변경은 트랜젝션 안에서 실행해야한다.
        try {
           // Member member = new Member();
            /*
            저장

            member.setId(2L);
            member.setName("HelloB");
            //Q:이렇게까지 했는데도 error가 난다 왜?
            //A:JPA에서는 꼭 트랜젝션이라는 단위가 중요하다.(모든 데이터를 변경하는 모든작업은 traction안에서 작업해야한다.)  tx.begin(); tx.commit
            em.persist(member);
            */

            
            /*
            * 삭제
            * 
            * 
            * Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);
            * */
            
            /*
            * 수정
            *
            *
            *  Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
            * */

            /*
            *단순 조회
            *
            *
            *  Member findMember = em.find(Member.class, 1L);//em을 java의 Colleciton으로 생각하면 된다. PK값을 넣어 찾음
            System.out.println("findMember.getId(); : "+findMember.getId());
            System.out.println("findMember.getName(); : "+findMember.getName());
            * */

            /*
            * JPQL 객체 지향 쿼리 언어
            *
            * JPQL은 테이블이아닌 엔티티 객체를 대상으로 쿼리
            *
            * SQL은 데이터베이스 테이블을 대상으로 쿼리
            *
            * */
            //JPQL로 나이가 18살 이상인 회원을 모두 검색하고 싶다면?
            //JPQL로 전체 회원 검색
            List<Member> result = em.createQuery("select m from Member as m", Member.class)//(query, type parameter)
                    .getResultList();
            //jpa입장에서는 코드를 짤때 table 기준으로 절때 코드를 짜지 않는다.
            //member 객체를 대상으로 쿼리를 한다. (대상이 table이아니고 객체)
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            //JPA 페이징 처리
            /*
            List<Member> result = em.createQuery("select m from Member as m", Member.class)//(query, type parameter)
                   .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
                    
                    1번부터 10개 가져와
            */


            //JPQL로 ID가 2 이상인 회원만 검색
            //JPQL로 이름이 같은 회원만 검색


            //em.persist(findMember); 상식적으로는 이렇게 바꾼 ID 를 저장해야할거같은데 저장하지않아도 된다.
            //왜일까? 이건 자바 COLLECTION을 다루는 것과 같이 설계가 되어있어서 그렇다.
            // Member findMember = em.find(Member.class, 1L); JPA를 통해서 이렇게 Entity를 가져오면 JPA가 얘를 관리한다.
            //JPA가 변경이됬는지 안됫는지 TRACTION COMMIT시간을 다 체크하고 뭔가 바꼇네 하면 UPDATE 쿼리를 만들어 날리고 TRACTION이 COMMIT 된다.


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
            //내부적으로 db connection을 물고 동작하기때문에 사용이 끝나면 종료시켜줘야함
        }

        emf.close(); //전체 application 닫기
        /*
        * 이코드는 좋지않은 코드다 만약 em.persist(member);나 tx.commit();에서 문제가 생겻을때
        * 밑에있는 close가 실행되지 않기 때문이다. 정석코드는 traction을 try catch안에다가 감싸서 실행시켜야한다.
        *
        * 근데 실제는 이렇게 까지 할 필요가없음 spring이 다해주기 때문에.
        * */

    }
}
