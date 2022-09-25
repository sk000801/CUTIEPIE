package jpa.practice.controller;

import jpa.practice.SessionManager;
import jpa.practice.form.MemberForm;
import jpa.practice.member.Member;
import jpa.practice.member.MemberAccount;
import jpa.practice.member.MemberSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Transactional
//모든 매핑은 member를 상속함! 다는 의미래용
public class MemberSessionController {

    private final MemberSessionService memberSessionService;

    @PostMapping("/add")
    public void joinMember2(MemberForm form) {
        MemberAccount memberAccount = MemberAccount.create(form.getMemberId(), form.getPw());
        Member member = Member.create(form.getName(), memberAccount);
        memberSessionService.save(member);
//         회원가입시 오류가 발생했을 때 어떻게 해결해야 할 지 생각해보기
    }

}
