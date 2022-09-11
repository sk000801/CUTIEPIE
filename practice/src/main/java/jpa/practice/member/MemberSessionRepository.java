package jpa.practice.member;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
@AllArgsConstructor
public class MemberSessionRepository {

    private static final Map<String, Member> store = new HashMap<>();

    public void save(Member member) {
        store.put(member.getMemberId(), member);
    }

    public Member findById(String id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clear() {
        store.clear();
    }
}
