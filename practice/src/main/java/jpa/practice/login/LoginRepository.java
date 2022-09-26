package jpa.practice.login;

import jpa.practice.member.Member;
import jpa.practice.member.MemberAccount;
import jpa.practice.member.MemberSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
@Transactional
public class LoginRepository {

    private final MemberSessionService memberSessionService;

    public Member login(String id, String pw) {
         MemberAccount memberAccount = memberSessionService.findByUID2(id);
         Member member = memberSessionService.findByAccount(memberAccount);
         if(memberAccount.getPw().equals(pw)) {
             return member;
         }
         else return null;
        //id와 pw가 같은 멤버를 return 해주던가 없으면 그냥 null을 반환해주던가
    }

    public Object failId(String id) {
        MemberAccount memberAccount = memberSessionService.findByUID2(id);
        return memberSessionService.findByAccount(memberAccount);
    }
}
