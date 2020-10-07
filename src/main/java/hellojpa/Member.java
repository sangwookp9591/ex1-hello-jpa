package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity //이걸 해줘야 jpa가 처음 로딩될떄 jpa를 관리하는 애라고 인식을함.(어떤 table과 매핑해서쓰는애라고 생각)
//@Entity(name = "Member")
//@Table(name = "USER")//db의 이름이 먄약 Member가 아니고 user라고 되어있으면? 쿼리가 나갈때 USER라는 테이블에 적용하라고 나감.
public class Member {
/*
    @Id //id가 pk라는것을 알려줘야한다.
    private Long id;
    //@Column(name = "username") db컬럼이름이 name이 아니고 username일 경우
    //@Column(unique = true,length = 10) 이런식으로 unique제약조건 및 varchar 10 이런식으로도 설정할 수 있다.
                                        //DDL 생성기능은 DDL을 자동생성할 때만 사용되고 JPA의 실행 로직에는 영향을 주지 않는다.
    private String name;

    *//*JPA는 application loading 시점에 db table을 생성하는 기능을 지원.
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
    * *//*

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
    }*/

/*


      필드와 컬럼 매핑 20201007

      1. 회원은 일반 회원과 관리자로 구분해야 한다.
      2. 회원 가입일과 수정일이 있어야 한다.
      3. 회원을 설명할 수 있는 필드가 있어야 한다. 이 필드는 길이 제
       한이 없다
 .*//*
    @Id
    private Long id;
    @Column(name = "name", insertable = true,updatable = true) //insert/updat/able 컬럼을 수정했을때 뭔가 DB에 INSERT할거냐 말거냐 반영을 할것인가 말것인가? 기본은 TRUE이다.
    //nullable = false => 기본이 true인데 false이면 not null 제약조건
    //unique = true -> unique 제약조건을 만들어주지만 이름을 렌덤처럼 만들기 때문에 모르기때문! 이름을 반영하기 어렵고
    //복합키로도 사용불가 그렇기때문에 unique 제약조건은 @Table(uniqueConstraints = )로 이름까지 걸어서 줄 수 있다.
    //columnDefinition = varchar(100) default 'EMPTY' 데이터 베이스 컬럼 정보를 직접(특정 db에 종속적인 옵션까지) 줄 수있다.
    private String username;
    private Integer age;

    @Enumerated(EnumType.STRING)//ENUM 타입을 쓸때 주의할점
    //@Enumerated // 기본 EnumType.ODRINAL ->순서 -> DB등록시 기본적으로 숫자가 들어감.
    //근데 이걸 쓰면 안되는이유  -> USER ADMIN 다음에 GUEST가 추가됫을경우(GUEST가 ENUM애 제일처음) A 0 ,B 1 C 0이됨.
    //즉 순서가 바뀐거기때문에 운영에 엄청 큰영향을 미친다.. 위험하다..
    //그래서 STRING으로써야함.. STRING이면 ENUM에 등록한 명칭대로 들어간다.
    private RoleType roleType;



    *//*
    JAVA 8 이상의경우
    * 최근에는  LocalDate, LocalDateTime이 들어왔기때문에 아래를 생략가능
    *private LocalDate test1; //년월

    private LocalDateTime test12; //년월일
    * *//*
    @Temporal(TemporalType.TIMESTAMP) //DATE날짜  TIME시간 TIMESTAMP날짜시간
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;


    //Lob은지정할 수 있는 속성이 없다.
    @Lob///varchar데 큰 contents를 넣고 싶을때 @Lob 후 문자타입이면 clob, 나머지는 blob
    private String description;
    //Getter, Setter…

    @Transient//db랑 전혀관계없이 메모리에서만 쓰고싶어, DB랑 연결하지말아줘
    private int temp;

    public Member(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }*/

    /*기본키 매핑 20201008
    *
    * • 직접 할당: @Id만 사용
      • 자동 생성(@GeneratedValue)
        • IDENTITY: 데이터베이스에 위임, MYSQL
        • SEQUENCE: 데이터베이스 시퀀스 오브젝트 사용, ORACLE
            • @SequenceGenerator 필요
        • TABLE: 키 생성용 테이블 사용, 모든 DB에서 사용
            • @TableGenerator 필요
        • AUTO: 방언에 따라 자동 지정, 기본값
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //AUTO는 DB방언에 맞춰서  만들어지지만 확인해 봐야한다.
    //.IDENTITY 전략 -기본 키 생성을 DB에 위임 MySQL, PostgreSQL, SQL Server, DB2
    //.sequence 전략 -데이터베이스 시퀀스는 유일한 값을 순서대로 생성하는 특별한
                     //데이터베이스 오브젝트(예: 오라클 시퀀스)
                     //오라클, PostgreSQL, DB2, H2 데이터베이스에서 사용
   /*SEQUENCE 전략 - 매핑
   * @Entity
    @SequenceGenerator(
     name = “MEMBER_SEQ_GENERATOR",
     sequenceName = “MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
     initialValue = 1, allocationSize = 1)
        public class Member {
         @Id
         @GeneratedValue(strategy = GenerationType.SEQUENCE,
         generator = "MEMBER_SEQ_GENERATOR")
         private Long id;
   *
   * */

    /*
    *
    * .TABLE 전략
        • 키 생성 전용 테이블을 하나 만들어서 데이터베이스 시퀀스를 흉
        내내는 전략
        • 장점: 모든 데이터베이스에 적용 가능
        • 단점: 성능 - table을 직접사용하기 때문에(sequence object와 같은 것들은 숫자뽑는데 최적화가 되어있는데 이건 되어있지 않기때문에)
       * */

    /*
    *
    *
    * 권장하는 식별자 전략
        • 기본 키 제약 조건: null 아님, 유일, 변하면 안된다.
        • 미래까지 이 조건을 만족하는 자연키는 찾기 어렵다. 대리키(대체키)를 사용하자.
        • 예를 들어 주민등록번호도 기본 키로 적절하기 않다.
        • 권장: Long형 + 대체키 + 키 생성전략 사용 -> business를 key로 끌고오면 안된다.

    *
    * */
    private Long id;//Long을 쓰는 이유? 지금은 application에 long을써도 영향을 주지않기때문에 사용

    @Column(name = "name", insertable = true,updatable = true) //insert/updat/able 컬럼을 수정했을때 뭔가 DB에 INSERT할거냐 말거냐 반영을 할것인가 말것인가? 기본은 TRUE이다.
    private String username;

    public Member(){

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