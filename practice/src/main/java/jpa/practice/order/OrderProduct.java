package jpa.practice.order;

import jpa.practice.controller.ProductForm;
import jpa.practice.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="order_product")
//아 이거 내가 정확히 공부 안했나보다ㅠㅠ 기억이 안나ㅠㅠ
public class OrderProduct {

    @Id
    @GeneratedValue
    @Column(name="order_product_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int price;

    private int count;

    public void cancel() {
        getProduct().addStock(count);
    }

    public int total() {
        return getCount()*getPrice();
    }
}
