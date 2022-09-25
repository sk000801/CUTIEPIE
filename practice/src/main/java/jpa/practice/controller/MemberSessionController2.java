package jpa.practice.controller;

import jpa.practice.member.Member;
import jpa.practice.member.MemberSessionService;
import jpa.practice.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberSessionController2 {

    private final MemberSessionService memberSessionService;

    @ResponseBody
    @GetMapping("/member/orders/{id}")
    public List<Order> memberOrderList(@PathVariable("id") String id
            ,@SessionAttribute(name="mySessionId", required = false) Member member) {
        return memberSessionService.findByUID(id).getPostOrder();
    }
}
