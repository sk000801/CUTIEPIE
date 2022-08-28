//package jpa.practice.login;
//
//import jpa.practice.member.Member;
//import jpa.practice.member.MemberSessionService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//@Repository
//@RequiredArgsConstructor
//@Transactional
//public class LoginRepository {
//
//    private final MemberSessionService memberSessionService;
//
//    public Member login(String id, String pw) {
//        return memberSessionService.findById(id)
//                .filter(m -> m.getPw().equals(pw))
//                .orElse(null);
//        //id와 pw가 같은 멤버를 return 해주던가 없으면 그냥 null을 반환해주던가
//    }
//}
