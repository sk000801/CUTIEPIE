//package jpa.practice.controller;
//
//import jpa.practice.member.Member;
//import jpa.practice.member.MemberService;
//import jpa.practice.member.MemberStatus;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class MemberController {
//
//    private final MemberService memberService;
//
//    @GetMapping("/members/join")
//    public String join(Model model) {
//        model.addAttribute("form", new MemberForm());
//        return "members/joinMember";
//    }
//
//    @PostMapping("/members/join")
//    public String join2(MemberForm form) {
//        Member member = new Member();
//        member.setName(form.getName());
//        member.setPw(form.getPw());
//        member.setStatus(form.getStatus());
//
//        memberService.join(member);
//        return "redirect:/";
//    }
//
////    @GetMapping("/members/login")
////    public String login(Model model) {
////        model.addAttribute("form", new MemberForm());
////        return "members/loginMember";
////    }
////
////    @PostMapping("/members/login")
////    public String login2(MemberForm form, Model model) {
////        Member member = memberService.findName(form.getName());
////        model.addAttribute("member", member);
////
////        String a = null;
////        if(member.getStatus() == MemberStatus.member) {
////            a = "mainPage2";
////        }
////        else if(member.getStatus() == MemberStatus.admin) {
////            a = "mainPage3";
////        }
////        return a;
////    }
//
//    @GetMapping("/members/list")
//    public String list(Model model) {
//        List<Member> members = memberService.findAll();
//        model.addAttribute("members", members);
//        return "members/MemberList";
//    }
//
//    @GetMapping("/members/search")
//    public String search(@ModelAttribute("name") String name, Model model) {
//        List<Member> results = memberService.search(name);
//        model.addAttribute("members", results);
//        return "members/memberSearch";
//    }
//}
