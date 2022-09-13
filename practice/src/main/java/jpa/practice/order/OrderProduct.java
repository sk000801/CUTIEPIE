package jpa.practice.order;

import jpa.practice.controller.ProductForm;
import jpa.practice.product.Product;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String id = UUID.randomUUID().toString();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id")
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
