package jpa.practice.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpa.practice.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="order_product")
@NoArgsConstructor
// 상품 종류마다 하나의 객체가 되어 주문서에 들어갈 준비를 마치는 것!
public class OrderProduct {

    @Id
    @Column(name="order_product_id")
    @JsonIgnore
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonIgnore
    private Order order;

    private int price;
    private int count;

    public static OrderProduct create(Product product, int count, int price) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setPrice(price);
        orderProduct.setCount(count);

        orderProduct.add();
        return orderProduct;
    }

    public void cancel() {
        getProduct().addStock(count);
    }

    public void add() {
        getProduct().removeStock(count);
    }

    public int total() {
        return getCount()*getPrice();
    }
}
