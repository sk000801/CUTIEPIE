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
    @JsonIgnore
    private Long id;

    //fetch = FetchType.LAZY,
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="basket_id")
    @JsonIgnore
    private MemberBasket memberBasket;

    //fetch = FetchType.LAZY,
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="product_id")
    @JsonIgnore
    private Product product;

    private String name;
    private int count; //선택함 상품의 개수

    public static BasketProduct create(Product product, int count, String name) {
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setProduct(product);
        basketProduct.setName(name);
        basketProduct.setCount(count);
        return basketProduct;
    }

    public int totalPrice() {
        return (getProduct().getPrice()*count);
    }
}
