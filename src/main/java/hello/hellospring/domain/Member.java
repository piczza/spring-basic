package hello.hellospring.domain;

public class Member {

    private Long id;        //고객이 아니라 시스템상의 id
    private String name;    //이름

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
