package jpa.practice.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class ImageStore {

    @Id
    @Column(name="image_id")
    private String image_id = UUID.randomUUID().toString();

    @Column(name="original_name")
    private String uploadFilename;

    @Column(name="filename")
    private String storeFilename;

}
