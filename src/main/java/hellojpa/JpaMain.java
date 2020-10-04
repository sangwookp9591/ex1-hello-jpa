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
            *
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
          /*  List<Member> result = em.createQuery("select m from Member as m", Member.class)//(query, type parameter)
                    .getResultList();
            //jpa입장에서는 코드를 짤때 table 기준으로 절때 코드를 짜지 않는다.
            //member 객체를 대상으로 쿼리를 한다. (대상이 table이아니고 객체)
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
*/
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


             /*
            비영속 상태
            JPA랑 전혀 관계가 없는 상태
            객체만 생성한 상태
            Member member = new Member();
            member.setId("member1");
            member.setUsername("회원1");

            영속성 상태
            Member member = new Member();
            member.setId("member1");
            member.setUsername("회원1");

            EntityManager em = emf.createEntityManger();
            em.getTransaction().begin();
            //객체를 저장한 상태(영속)
            em.persist(member); em의 영속 컨텍스트에 객체가 들어가면서 영속상태가 된다.
            
            
            영속성 컨텍스트의 이점(중간에 있음으로써 얻는 이점)
            
            1. 1차 캐시(db 한 transaction 안에서만 효과가 있음)
                찾고자하는 key가 1차 캐시에 있으면 1차캐시에서 바로반환
                없으면 db에서 조회하고 1차캐시에 저장한 후 반환
            2. 영속 엔티티의 동일성(identity) 보장
                Mmeber a = em.find(Member.class,"member1");
                Mmeber b = em.find(Member.class,"member1");
            3. 트랜잭션을 지원하는 쓰기 지연(transcational write-behind)
                em.persist(MemberA); 쓰기 지연 SQL 저정소에 SQL INSERT 쌓음
                em.persist(MemberB); 쓰기 지연 SQL 저정소에 SQL INSERT 쌓음
                여기까지 INSERT SQL을 DB에 보내지 않는다.
                커밋하는 순간 쓰기 지연 SQL 저장소에있는 DB에 INSERT SQL이 flush가 된다.
                실제 db에 commit된다.

            4. 변경 감지(Dirty Checking)
               자바 collection에서 하는것처럼 db가 변경
               1.JPA는 COMMIT하는 시점에 내부적으로 flush라는게 호출된다.,=
               2.entity랑 스냅샷(내가 값을 읽어온 최초시점의 값)을 비교
               3.memberA가 바꼇네? 그럼 UPDATE SQL을 쓰기 지연 SQL 저장소에 만들어둠
               4.이걸 DB에 반영하고 COMMIT한다
            
            5. 지연 로딩(lazy Loading)
            
            
            ==플러시==

               - 영속성 컨텍스트의 변경내용을 DB에 반영
               - 영속성 컨텍스트의 변경사항과 DB를 맞추는 작업
               - 영속성 컨텍스트의 QUERY(INSERT, UPDATE, DELETE)들을 DB에 날려주는 작업

            DB TRANSACITON이 COMMIT될때 자동적으로 FLUSH 발생
            플러시가 발생되면?
                1. 변경 감지
                2. 수정된 엔티티 쓰기 지연 SQL 저장소에 등록
                3. 쓰기 지연 SQL저장소의 쿼리를 DB에 전송(등록 ,수정, 삭제 쿼리)
                (1차 캐시의 내용이 지워지지는 않음)
            
            영속성 컨텍스트를 플러시 하는 방법
                1. em.flush() - 직접호출(미리 쿼리날라가는것을 보고싶을떄)
                2. 트랜잭션 커밋 - 플러시 자동 호출
                3. JPQL 쿼리 실행 - 플러시 자동 호출

             플러시 모드 옵션
                 EX ) em.setFlushMode(FlushModeType.COMMIT)

                 FlushModeType.AUTO
                 커밋이나 쿼리를 실행할 때 플러시(기본값)

                 FlushModeType.COMMIT
                 커밋할 때만 플러시시
                 가급 적이면 손안대고 AUTO쓰는걸 권장.. 아무것도 설정안하면 AUTO이다.
                 
             플러시는!!
                -영속성 컨텍스트를 비우지 않음.
                
                - 영속성 컨텍스트의 변경내용을 DB에 동기화
                
                - 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화하면 됨
                
        * */

            //비영속
           Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            //영속
            System.out.println("===BEFORE===");
            em.persist(member); //em안에있는 영속성 컨텍스트에서 member가 관리가 된다.
            // em.persist(member); 까지는 실제 DB에는 저장이 되지 않는다. 그럼 어디서 DB에 저장이 되는 것인가?

            //준영속
            //detach하게되면 영속성 컨텍스트에서 지움.
            //회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
            //em.detach(member);
            
            //삭제
            //객체를 삭제한 상태(실제 영구저장한 db에서 지우겠어)
            //em.remove(member);

            System.out.println("===AFTER===");

            
            tx.commit(); //transaction을  commit하는 시점에 영속성 컨텍스트에 있는애가 db에 쿼리가 날라감.
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
