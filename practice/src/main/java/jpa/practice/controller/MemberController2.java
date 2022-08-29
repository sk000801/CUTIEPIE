package jpa.practice.controller;

import jpa.practice.member.Member;
import jpa.practice.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController2 {

    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/members/list")
    public List<Member> memberList() {
        List<Member> members = memberService.findAll();

        return members;
    }
}
