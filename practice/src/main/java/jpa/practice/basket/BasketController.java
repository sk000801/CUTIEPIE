package jpa.practice.basket;

import jpa.practice.member.Member;
import jpa.practice.member.MemberSessionService;
import jpa.practice.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Transactional
public class BasketController {

    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final MemberSessionService memberSessionService;

    //담는 개수를 주소에다 보냄
    @PostMapping("/members/basket/{id}")
    public void add(@PathVariable("id") String id, @RequestParam("count") int count,
                    @SessionAttribute(name="mySessionId", required = false) Member member) {
        //,  BindingResult b

//        if(productRepository.findId(id).getStock() < count) {
//            b.addError(new FieldError("count","count", "장바구니에 담긴 상품 개수가 재고보다 많습니다!"));
//            //어우 근데 얘는 count 단독으로 url에서 받아오는 친구라 field가 있는지 모르겠다
//        }
        BasketProduct basketProduct = BasketProduct.create(productRepository.findId(id), count);

        MemberBasket memberBasket = MemberBasket.create(member, basketProduct);

        basketRepository.joinProduct(basketProduct);
        basketRepository.joinAll(memberBasket);

        member.setMemberBasket(memberBasket);
        memberSessionService.save(member);
    }
}
