package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //이걸 해줘야 jpa가 처음 로딩될떄 jpa를 관리하는 애라고 인식을함.(어떤 table과 매핑해서쓰는애라고 생각)
//@Entity(name = "Member")
//@Table(name = "USER")//db의 이름이 먄약 Member가 아니고 user라고 되어있으면? 쿼리가 나갈때 USER라는 테이블에 적용하라고 나감.
public class Member {

    @Id //id가 pk라는것을 알려줘야한다.
    private Long id;
    //@Column(name = "username") db컬럼이름이 name이 아니고 username일 경우
    //@Column(unique = true,length = 10) 이런식으로 unique제약조건 및 varchar 10 이런식으로도 설정할 수 있다.
                                        //DDL 생성기능은 DDL을 자동생성할 때만 사용되고 JPA의 실행 로직에는 영향을 주지 않는다.
    private String name;

    /*JPA는 application loading 시점에 db table을 생성하는 기능을 지원.
    * 운영보다는 개발 or local환경에서 사용
    *
    * -보통은 table 만들고 객체로 돌아와서 매핑하는데 JPA는 그럴필요가 없다.
    *
    * -데이터베이스 방언을 활용해서 데이터베이스에 맞는 적절한 DDL 사용
    *   이렇게 생성된 DDL은 개발장비에서만 사용
    *   생성된 DDL은 운영서버에서는 사용하지 않거나, 적절히 다듬은 후 사용
    *persistence.xml에 <property name="hibernate.hbm2ddl.auto" value="create" /> 부분이다.
    *
    * member drop 후 create table 생성
    *
    *
    * */

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
/*
@Entity는 기본 생성자가 필수이다 (파라미터가 없는 public protected 생성자)
-JPA를 구현해서쓰는 라이브러리들이 리플랙션같은  다양한 기술을 써서 객체를 프록싱할려면 필요하기때문에
-final 클래스 ,enum,interface,inner 클래스 사용은 불가하다.
-저장할 필드에 final사용 안된다.

*
*
* */