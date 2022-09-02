package jpa.practice.product;


import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="product_image")
public class ProductImage {

    @Column(name="image_id")
    @Id
    private String image_id = UUID.randomUUID().toString();

    @Column(name="filename")
    private String filename;

    @Column(name="original_name")
    private String original_name;

}
