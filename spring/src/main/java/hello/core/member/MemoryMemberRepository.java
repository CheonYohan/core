package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {

    // 일반 해쉬맵을 사용하면 동시성 문제가 발생 할 수 있다. 실무에서 적용하려면 ConcurrentHashMap 을 사용해야한다. ConcurrentHashMap은 따로 공부해보자.
    private static Map<Long,Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
