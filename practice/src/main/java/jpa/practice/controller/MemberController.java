package jpa.practice.controller;

import jpa.practice.member.Member;
import jpa.practice.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/join")
    public String join(Model model) {
        model.addAttribute("form", new MemberForm());
        return "joinMember";
    }

    @PostMapping("/members/join")
    public String join2(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setPw(form.getPw());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members/list")
    public String list(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "MemberList";
    }

    @GetMapping("/members/search")
    public String search(@ModelAttribute("name") String name, Model model) {
        List<Member> results = memberService.search(name);
        model.addAttribute("members", results);
        return "memberSearch";
    }
}
