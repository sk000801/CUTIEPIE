package jpa.practice.controller;

import jpa.practice.SessionManager;
import jpa.practice.member.Member;
import jpa.practice.order.OrderProduct;
import jpa.practice.product.Product;
import jpa.practice.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class OrderProductController {

    private final ProductService productService;
    private final SessionManager sm;

    @GetMapping("orders/product/join")
    public String join() {
        return "/products/orderProduct";
    }

    @PostMapping("orders/product/join/{id]")
    public String join2(OrderProductForm form, HttpServletRequest request, @PathVariable("id") String id) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setPrice(form.getPrice());
        orderProduct.setCount(form.getCount());

        Member member = (Member) sm.getSession(request);

        Product product = productService.findId(id); //상품 주문시 주문 버튼 클릭하면 상품 정보가 넘어오도록
        orderProduct.setProduct(product);

//      order 어떻게 연관지을까 생각!
        return "/products/productsList";
    }
}
