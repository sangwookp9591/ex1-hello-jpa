package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //이걸 해줘야 jpa가 처음 로딩될떄 jpa를 사용하는 애라고 인식을함.
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
