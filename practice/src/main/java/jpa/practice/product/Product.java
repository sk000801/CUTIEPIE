package jpa.practice.product;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.util.UUID;

@Entity
@Table(name="products")
@Data
public class Product {

    @Id
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
