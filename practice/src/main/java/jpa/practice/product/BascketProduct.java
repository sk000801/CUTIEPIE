package jpa.practice.product;

import jpa.practice.controller.BascketController;
import jpa.practice.member.MemberBascket;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class BascketProduct {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bascket_id")
    private MemberBascket memberBascket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    private int count; //선택함 상품의 개수

    public static BascketProduct create(Product product, MemberBascket memberBascket, int count) {
        BascketProduct bascketProduct = new BascketProduct();
        bascketProduct.setProduct(product);
        bascketProduct.setMemberBascket(memberBascket);
        bascketProduct.setCount(count);
        return bascketProduct;
    }
}
