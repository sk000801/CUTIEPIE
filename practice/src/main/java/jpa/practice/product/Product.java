package jpa.practice.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="products")
@Getter
@Setter
public class Product {

    @Id
    @Column(name="product_id")
    private String id = UUID.randomUUID().toString();

    @Column(name="product_name")
    private String name;

    @Column(name="product_stock")
    private int stock;

    @Column(name="product_price")
    private int price;

    public void addStock(int number) {
        stock += number;
    }

    public void removeStock(int number) {
        stock -= number;
    }
}
