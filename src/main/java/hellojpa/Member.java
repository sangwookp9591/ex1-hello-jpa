package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //이걸 해줘야 jpa가 처음 로딩될떄 jpa를 사용하는 애라고 인식을함.
public class Member {

    @Id //id가 pk라는것을 알려줘야한다.
    private Long id;
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
