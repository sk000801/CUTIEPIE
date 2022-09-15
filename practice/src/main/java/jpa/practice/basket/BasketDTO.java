package jpa.practice.basket;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BasketDTO {
        private final List<BasketProduct> lists;

        public BasketDTO(MemberBasket memberBasket) {
                this.lists = memberBasket.getProducts();
        }
}
