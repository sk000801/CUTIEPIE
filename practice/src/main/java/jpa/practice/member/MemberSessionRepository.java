package jpa.practice.member;

import jpa.practice.basket.MemberBasket;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class MemberSessionRepository  {
 //implements JpaRepository<MemberAccount, String>
    private final EntityManager em;
    //private static final Map<String, Member> store = new HashMap<>();

    public void save(Member member) {
        em.merge(member); //혹시 모를 수정의 경우에 대비해
        //store.put(member.getMemberId(), member);
    }

    public void save_account(MemberAccount memberAccount) {
        em.merge(memberAccount);
    }

    public Member findByUID(String id) {
        return em.find(Member.class, id);
    }

    public MemberAccount findByUID2(String id) {
        return em.createQuery("select ma from MemberAccount ma where ma.id = :account_id", MemberAccount.class)
                .setParameter("account_id", id)
                .getSingleResult();
    }

    public Member findByAccount(MemberAccount memberAccount) {
        return (Member) em.createQuery("select m from Member m where m.memberAccount = :memberAccount", Member.class)
                .setParameter("memberAccount", memberAccount)
                .getSingleResult();
    }

    public String findByMember(Member member) {
        return em.createQuery("select m.id from Member m where m = :member", String.class)
                .setParameter("member", member)
                .getSingleResult();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class)
                .getResultList();
    }

}
