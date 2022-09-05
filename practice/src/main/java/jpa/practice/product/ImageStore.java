package jpa.practice.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@Component
public class ImageStore {

    @Column(name="original_name")
    private String uploadFilename;

    @Column(name="filename")
    private String storeFilename;

}
