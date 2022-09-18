package jpa.practice.product;

import jpa.practice.image.ImageStore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="products")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name="product_name")
    private String name;

    @Column(name="product_stock")
    private int stock;

    @Column(name="product_price")
    private int price;

    //fetch=FetchType.LAZY,
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="image_id")
    private ImageStore imageStore;

    @Column(length=10000)
    private String detail;

    @Column(name="category")
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @CreatedDate
    private LocalDateTime createdDate;

    public void addStock(int number) {
        stock += number;
    }

    public void removeStock(int number) {
        stock -= number;
    }

}
