package jpa.practice.basket;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BasketController2 {

    private final BasketRepository basketRepository;

    @ResponseBody
    @GetMapping("/members/basket")
    public List<MemberBasket> list() {
        return basketRepository.findAll();
    }
}
