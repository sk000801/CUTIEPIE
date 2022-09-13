package jpa.practice.product;

import jpa.practice.member.MemberBasket;
import jpa.practice.member.MemberBasket;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class BasketProduct {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="basket_id")
    private MemberBasket memberBasket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    private int count; //선택함 상품의 개수

    public static BasketProduct create(Product product, MemberBasket memberBasket, int count) {
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setProduct(product);
        basketProduct.setMemberBasket(memberBasket);
        basketProduct.setCount(count);
        return basketProduct;
    }

    public int totalPrice() {
        return (getProduct().getPrice()*count);
    }
}
