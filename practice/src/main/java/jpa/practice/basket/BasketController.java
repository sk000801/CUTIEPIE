package jpa.practice.basket;

import jpa.practice.member.Member;
import jpa.practice.member.MemberSessionService;
import jpa.practice.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BasketController {

    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final MemberSessionService memberSessionService;

    //담는 개수를 주소에다 보냄
    @PostMapping("/members/basket/{id}")
    public void add(@PathVariable("id") String id,  @RequestParam("count") int count,
                    @SessionAttribute(name="mySessionId", required = false) Member member) {

        BasketProduct basketProduct = BasketProduct.create(productRepository.findId(id), count, productRepository.findId(id).getName());
        MemberBasket memberBasket = MemberBasket.create(basketProduct);

        basketRepository.joinProduct(basketProduct);
        basketRepository.joinAll(memberBasket);

        member.setMemberBasket(memberBasket);
        memberSessionService.save(member);
    }
}
