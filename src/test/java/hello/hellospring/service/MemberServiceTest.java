package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void afterEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void adterEach(){
        memoryMemberRepository.clearStore();
    }
    @Test
    void 회원가입() {   //테스트는 메서드명을 한글로 해도 됨.
        //given: 계정 하나 생성
        Member member = new Member();
        member.setName("hello");
        //when: 계정으로 회원가입 됨
        Long saveId = memberService.join(member);
        //then: memberService에 계정이 잘 있는지, 가입된 계정이 생성된 계정과 같은지.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when: 예외 확인하는 방법1
        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        //when: 예외 처리하는 방법2
//        memberService.join(member1);
//        //assertThrows(예상되는예외.class, ()-> 예외가 발생할 상황): 예외가 잘 발생되면 통과.
//        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}