package jpa.practice.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table(name="product_image")
public class ImageStore {

//    @Id
//    private String id = UUID.randomUUID().toString();

    @Column(name="original_name")
    private String uploadFilename;

    @Column(name="filename")
    @Id
    private String storeFilename;

}
