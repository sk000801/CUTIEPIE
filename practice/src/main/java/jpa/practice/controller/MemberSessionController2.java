package jpa.practice.controller;

import jpa.practice.member.Member;
import jpa.practice.member.MemberOrderDTO;
import jpa.practice.member.MemberSessionService;
import jpa.practice.order.Order;
import jpa.practice.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberSessionController2 {

    private final OrderService orderService;

    @ResponseBody
    @GetMapping("/member/orders")
    public MemberOrderDTO memberOrderList(@SessionAttribute(name="mySessionId", required = false) Member member) {
        return new MemberOrderDTO(member, orderService);
    }

}
