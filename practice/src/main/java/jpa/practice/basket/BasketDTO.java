package jpa.practice.basket;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BasketDTO {
        private final List<BasketProduct> lists;
        private String basket_id;

        public BasketDTO(MemberBasket memberBasket) {
                this.basket_id = memberBasket.getBasket_id();
                this.lists = memberBasket.getProducts();
        }
}
