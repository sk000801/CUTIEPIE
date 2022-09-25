package jpa.practice.member;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberSessionRepository {

    private final EntityManager em;
    //private static final Map<String, Member> store = new HashMap<>();

    public void save(Member member) {
        em.merge(member); //혹시 모를 수정의 경우에 대비해
        //store.put(member.getMemberId(), member);
    }

    public Member findByUID(String id) {
        return em.find(Member.class, id);
    }

    public Member findById(String id) {
        return em.createQuery("select m from Member m where m.MemberAccount.id = :account_id", Member.class)
                .setParameter("account_id", id)
                .getSingleResult();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class)
                .getResultList();
        //return new ArrayList<>(store.values());
    }

//    public void clear() {
//        store.clear();
//    }
}
