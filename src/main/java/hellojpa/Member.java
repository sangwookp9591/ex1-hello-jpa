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
    private String name;

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