package jpa.practice.controller;

import jpa.practice.login.LoginForm;
import jpa.practice.member.Member;
//import jpa.practice.member.MemberService;
import jpa.practice.member.MemberService;
import jpa.practice.member.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Transactional
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/join")
    public String join(Model model) {
        model.addAttribute("form", new MemberForm());
        return "members/joinMember";
    }

    @PostMapping("/members/join")
    public String join2(MemberForm form) {
        Member member = new Member();
        member.setMemberId(form.getMemberId());
        member.setName(form.getName());
        member.setPw(form.getPw());
        member.setStatus(form.getStatus());

        memberService.join(member);
        return "mainPage1";
    }

    @GetMapping("/members/login")
    public String login(Model model) {
        model.addAttribute("form", new LoginForm());
        return "members/loginMember";
    }

    @PostMapping("/members/login")
    public String login2(LoginForm form, Model model) {

        Member member = memberService.findOne(form.getMemberId());

        if(member == null) {
            return "members/loginMember";
        }
        if(!Objects.equals(member.getPw(), form.getPw())) {
            return "members/loginMember";
        }

        model.addAttribute("member", member);

        String a = null;
        if(member.getStatus() == MemberStatus.member) {
            a = "mainPage2";
        }
        else if(member.getStatus() == MemberStatus.admin) {
            a = "mainPage3";
        }
        return a;
    }

    @GetMapping("/members/logout")
    public String logout() {
        return "mainPage1";
    }

    @GetMapping("/members/search")
    public String search(Model model, @RequestParam(name="name", required = false) String name) {
        List<Member> results;
        if(name != null) {
            results = memberService.search(name);
        }
        else {
            results = memberService.findAll();
        }
        model.addAttribute("members", results);
        return "members/memberSearch";
    }

    @GetMapping("/members/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        Member member = memberService.findById(id);

        MemberForm form = new MemberForm();

        form.setMemberId(member.getMemberId());
        form.setName(member.getName());
        form.setPw(member.getPw());

        model.addAttribute("form", form);
        return "members/editMember";
    }

    @PostMapping("/members/edit/{id}")
    public String edit2(MemberForm form) {
        Member member = new Member();

        member.setMemberId(form.getMemberId());
        member.setPw(form.getPw());
        member.setName(form.getName());

        memberService.join(member);

        return "mainPage2";
    }
}
