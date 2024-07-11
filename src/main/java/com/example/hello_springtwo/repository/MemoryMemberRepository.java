package com.example.hello_springtwo.repository;

import com.example.hello_springtwo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    /**
     * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap,
     * AtomicLong 사용 고려
     * */
    private  static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        //store에 넣기 전 id값 세팅
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        /**
            null이 반환될 가능성이 있음
         Optional 사용
         **/
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
