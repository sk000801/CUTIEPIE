package jpa.practice.controller;

import jpa.practice.SessionManager;
import jpa.practice.member.Member;
import jpa.practice.order.Order;
import jpa.practice.order.OrderProduct;
import jpa.practice.order.OrderProductRepository;
import jpa.practice.order.OrderService;
import jpa.practice.product.Product;
import jpa.practice.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;
    private final OrderProductRepository orderProductRepository;

    @GetMapping("/orders/join/{id}")
    public String join(@PathVariable("id") String id, Model model) {
        model.addAttribute("OrderForm", new OrderProductForm());
        return "orders/joinOrder";
    }

    @PostMapping("/orders/join/{id}")
    public String join2(@SessionAttribute(name="mySessionId", required = false) Member member,
                            @Valid OrderProductForm form, @PathVariable("id") String id,
                          HttpServletRequest request) {;

        OrderProduct orderProduct = OrderProduct.create(productService.findId(id), form.getCount(), form.getPrice());

        Order order = Order.create(member, orderProduct);
        orderService.join(order);
        orderProductRepository.join(orderProduct);
        //이렇게 넣어야 order가 orderProduct에 들어간 채로 em에 저장이 됨

        return "redirect:/";
    }
}
