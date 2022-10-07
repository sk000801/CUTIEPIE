package jpa.practice.login;

import jpa.practice.member.Member;
import jpa.practice.member.MemberAccount;
import jpa.practice.member.MemberSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class LoginRepository {

    private final MemberSessionService memberSessionService;
    private final EntityManager em;

    public Optional<Member> login(String id, String pw) {
        Optional<MemberAccount> memberAccount = memberSessionService.findByUID2(id);
        Optional<Member> member = null;
        if (memberAccount.isPresent()) { //id가 존재한다면
            Member member1 =  em.createQuery("select m from Member m where m.memberAccount = :memberAccount", Member.class)
                    .setParameter("memberAccount", memberAccount)
                    .getSingleResult();
            if (member1.getMemberAccount().getPw().equals(pw)) {
                member = Optional.of(member1);
            }
            else {
                System.out.println("비밀번호가 틀렸습니다. 정확히 입력해주세요");
            }
        } else { //일치하는 id가 없다면
            System.out.println("존재하는 아이디가 없습니다. 정확히 입력해주세요");
        }
        return member;
    }
}







