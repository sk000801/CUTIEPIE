package jpa.practice.basket;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BasketDTO {
        private final List<BasketProduct> lists;
        private final String name;

        public BasketDTO(MemberBasket memberBasket, BasketProduct basketProduct) {
                this.lists = memberBasket.getProducts();
                this.name = basketProduct.getProduct().getName();
        }
}
