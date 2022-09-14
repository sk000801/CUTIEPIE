package jpa.practice.basket;

import jpa.practice.member.Member;
import jpa.practice.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class BasketController {

    private final ProductRepository productRepository;

    private final BasketRepository basketRepository;

    //아니 근데 여기서 어떻게 받아와 form 태그가 아니면 담는 개수를
    //장바구니에 담는 버튼을 누르면 axios로 post 요청을 한다고 했는디
    @PostMapping("/members/basket/{id}")
    public void add(@PathVariable("id") String id,  int count,
                    @SessionAttribute(name="mySessionId", required = false) Member member) {

        BasketProduct basketProduct = BasketProduct.create(productRepository.findId(id), count);
        MemberBasket memberBasket = MemberBasket.create(member, basketProduct);

        basketRepository.joinProduct(basketProduct);
        basketRepository.joinAll(memberBasket);
    }

}
