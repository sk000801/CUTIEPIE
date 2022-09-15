package jpa.practice.basket;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BasketController2 {

    private final BasketRepository basketRepository;

    @ResponseBody
    @GetMapping("/members/basket")
    public List<BasketProduct> list(@RequestParam("id") String id) {
        MemberBasket memberBasket = basketRepository.findById(id);
        return memberBasket.getProducts();
    }
}
