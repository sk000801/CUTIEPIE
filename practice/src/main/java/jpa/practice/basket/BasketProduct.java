package jpa.practice.basket;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jpa.practice.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class BasketProduct {

    @Id
    @GeneratedValue
    private Long id;

    //fetch = FetchType.LAZY,
    @ManyToOne(  cascade = CascadeType.PERSIST)
    @JoinColumn(name="basket_id")
    private MemberBasket memberBasket;

    //fetch = FetchType.LAZY,
    @ManyToOne(  cascade = CascadeType.PERSIST)
    @JoinColumn(name="product_id")
    private Product product;

    private int count; //선택함 상품의 개수

    public static BasketProduct create(Product product, int count) {
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setProduct(product);
        basketProduct.setCount(count);
        return basketProduct;
    }

    public int totalPrice() {
        return (getProduct().getPrice()*count);
    }
}
