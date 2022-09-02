package jpa.practice.product;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="products")
@Data
public class Product implements Persistable<String> {

    @Id
    private String id = UUID.randomUUID().toString();

    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name="product_name")
    private String name;

    @Column(name="product_stock")
    private int stock;

    @Column(name="product_price")
    private int price;

    @OneToOne
    @JoinColumn(name="image_id")
    private ProductImage productImage;

    public void addStock(int number) {
        stock += number;
    }

    public void removeStock(int number) {
        stock -= number;
    }

    @Override
    public boolean isNew() {
        return createdDate==null;
    }
}
