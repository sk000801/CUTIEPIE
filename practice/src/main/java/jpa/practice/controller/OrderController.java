package jpa.practice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    @GetMapping("/order/join")
    public String join() {
        return "orders/joinOrder";
    }

    @PostMapping("/order/join")
    public String join2() {
        return "redirect:/";
    }
}
