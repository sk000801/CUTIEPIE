package jpa.practice.product;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProductImage {

    @OneToOne
    @JoinTable(name="product",
            joinColumns = @JoinColumn(name="image_id"),
            inverseJoinColumns = @JoinColumn(name="id"))
    private String image_id;

    @Column(name="filename")
    private String filename;

    private String original_name;

}
