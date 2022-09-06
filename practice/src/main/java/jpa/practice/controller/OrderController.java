package jpa.practice.controller;

import jpa.practice.SessionManager;
import jpa.practice.member.Member;
import jpa.practice.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final SessionManager sm;

    @GetMapping("/orders/join")
    public String join() {
        return "orders/joinOrder";
    }

    @PostMapping("/orders/join")
    public String join2(HttpServletRequest request) {
        Order order = new Order();
        Member member = (Member) sm.getSession(request);
        //근데 store에서 정보는 어떻게 불러와?
        order.setMember(member);
//        order.setPName(form.getPName());
//        order.setPNumber(form.getPNumber());
        //OrderProduct 어케 구상할 지 생각!
        return "redirect:/";
    }
}
