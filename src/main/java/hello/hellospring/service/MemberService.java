package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        //동일한 이름의 회원은 받지 않음
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


    private void validateDuplicateMember(Member member) {
        //Optional<Member> result = memberRepository.findByName(member.getName());
        //result.ifPresent(m->{   //값이 있으면 동작. optional이라 가능한 동작.
        //throw new IllegalStateException("이미 존재하는 회원입니다.");
        //});
        //이 위 주석처리된 코드와 아래 코드는 완전 동일.
        //변수를 예외처리하면 자동으로 optional이 되고, optional의 메서드를 메서드체이닝 할수있다.
        memberRepository.findByName(member.getName())
            .ifPresent(m->{   //값이 있으면 동작. optional이라 가능한 동작.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }
}
