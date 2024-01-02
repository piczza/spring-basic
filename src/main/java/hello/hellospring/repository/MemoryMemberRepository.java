package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);           //id는 시스템이 자동할당, name은 고객이 직접할당
        store.put(member.getId(), member);  //두 정보값 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //반환값이 null일 가능성이 있다면 반환과 동시에 optional로 감싸기 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //자동으로 optional로 반환된다고 함
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
