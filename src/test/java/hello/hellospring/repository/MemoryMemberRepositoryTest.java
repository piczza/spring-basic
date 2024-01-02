package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //@Test 붙여주고 임포트 하면 해당 메서드를 테스트 할 수 있게 됨.
    @Test
    public void save(){
        //메인 메소드 쓰듯이 쓰면 됨. 필요 변수 선언,메서드 사용, 결과 호출까지 다 함.
        Member member = new Member();
        member.setName("모모");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();  //Optional에서 꺼낼때는 get()으로 꺼내야함.

//        잘 되었는지 확인할 수 있는 방법 1. Assertions.assertEquals(예상기대 값, 시험할 값);
        //잘 설계 되면 실행했을 때 초록불, 아니면 빨간불 뜸.
//        Assertions.assertEquals(member, result);

        //잘 되었는지 확인할 수 있는 방법 2. Assertions.assertThat(시험할값).isEqualTo(예상기대값);
        assertThat(result).isEqualTo(member);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("member1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("member2");
        repository.save(member2);

        Member result = repository.findByName("member1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("member1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("member2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size() == 2);
    }

    //어떤 메소드든 끝날때마다 호출됨.
    //테스트하는 메서드가 많아질수록 연산이 계속 누적되면서 오류 발생 가능성 높아짐.
    //때문에 이렇게 메서드 하나 테스트 끝날때마다 오류를 발생시킬만한 변수 초기화해줄것.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

}
