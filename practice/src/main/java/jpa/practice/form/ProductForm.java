package jpa.practice.form;

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

    private MultipartFile file;


}