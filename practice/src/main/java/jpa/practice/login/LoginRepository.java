package jpa.practice.login;

import jpa.practice.member.Member;
import jpa.practice.member.MemberAccount;
import jpa.practice.member.MemberSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class LoginRepository {

    private final MemberSessionService memberSessionService;
    private final EntityManager em;

    public Member login(String id, String pw) {
        Optional memberAccount = null;
        try {
            memberAccount = memberSessionService.findByUID2(id);
            Member member =  (Member) em.createQuery("select m from Member m where m.memberAccount = :memberAccount", Member.class)
                    .setParameter("memberAccount", memberAccount)
                    .getSingleResult();
            if(memberAccount.equals()) {
                return member;
            }
            else return null;
        }
        catch(Exception e) {
            System.out.println("로그인에 실패하셨습니다. 정확히 입력해주세요.");
        }
        //id와 pw가 같은 멤버를 return 해주던가 없으면 그냥 null을 반환해주던가
    }

    public Object failId(String id) {
        MemberAccount memberAccount = memberSessionService.findByUID2(id);
        return memberSessionService.findByAccount(memberAccount);
    }
}
