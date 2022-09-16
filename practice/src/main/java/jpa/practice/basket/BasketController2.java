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
    public BasketDTO list(@RequestParam("id") String basket_id) {
        return new BasketDTO(basketRepository.findById(basket_id));
    }

    //여기서 BasketProduct를 불러와야 그 product 이름만 쓸 수 있는데ㅠㅠ
}
