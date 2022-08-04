package jpa.practice.controller;

import jpa.practice.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/join")
    public String join2(MemberForm form) {

    }

    @GetMapping("/member/join")
    public String join() {
        return "joinMember";
    }
}
