package jpa.practice.form;

import jpa.practice.product.ProductCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinTable;
import javax.validation.constraints.NotEmpty;
import java.io.File;

@Getter
@Setter
public class ProductForm {


    @NotEmpty
    private String pName;

    private int price;

    private int stock;

    private String detail;

    private ProductCategory category;

    private MultipartFile file;


}
