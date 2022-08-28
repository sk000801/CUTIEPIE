package jpa.practice.controller;

import jpa.practice.member.Member;
import jpa.practice.member.MemberStatus;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

//@Controller
//@RequiredArgsConstructor
//@Data
//@Transactional
//public class MemberSqlController {
//
//    private final EntityManager em;
//
//    @GetMapping("/members/join")
//    public String join(Model model) {
//        model.addAttribute("form", new MemberForm());
//        return "members/joinMember";
//    }
//
//    @PostMapping("/members/join")
//    public String join2(MemberForm form) {
//
//        em.createNativeQuery("INSERT INTO members (member_id, pw, name, status) VALUES (?, ?, ?, ?)")
//                .setParameter(1, form.getMemberId())
//                .setParameter(2, form.getPw())
//                .setParameter(3, form.getName())
//                .setParameter(4, form.getStatus().toString())
//                .executeUpdate();
//
//        return "mainPage1";
//    }

//    @GetMapping("/members/login")
//    public String login(Model model) {
//        model.addAttribute("form", new MemberForm());
//        return "members/loginMember";
//    }

//    @PostMapping("/members/login")
//    public String login2(MemberForm form, Model model) {
//        Member member = memberService.findName(form.getName());
//        model.addAttribute("member", member);
//
//        String a = null;
//        if(member.getStatus() == MemberStatus.member) {
//            a = "mainPage2";
//        }
//        else if(member.getStatus() == MemberStatus.admin) {
//            a = "mainPage3";
//        }
//        return a;
//    }
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
