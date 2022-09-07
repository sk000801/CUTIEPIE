package jpa.practice.controller;

import jpa.practice.SessionManager;
import jpa.practice.member.Member;
import jpa.practice.order.Order;
import jpa.practice.order.OrderProduct;
import jpa.practice.order.OrderProductRepository;
import jpa.practice.order.OrderService;
import jpa.practice.product.Product;
import jpa.practice.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final SessionManager sm;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderProductRepository orderProductRepository;

    @GetMapping("/orders/join/{id}")
    public String join(@PathVariable("id") String id) {
        return "orders/joinOrder";
    }

    @PostMapping("/orders/join/{id}")
    public String join2(HttpServletRequest request, OrderProductForm form, @PathVariable("id") String id) {
        Member member = (Member) sm.getSession(request);
        //근데 store에서 정보는 어떻게 불러와?

        OrderProduct orderProduct = OrderProduct.create(productService.findId(id), form.getCount(), form.getPrice());

        Order order = Order.create(member, orderProduct);
        orderService.join(order);
        orderProductRepository.join(orderProduct);

        order.add();

        return "redirect:/";
    }
}
