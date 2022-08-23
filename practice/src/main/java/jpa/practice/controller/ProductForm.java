package jpa.practice.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ProductForm {

    private String id;
    @NotEmpty
    private String pName;

    private int price;

    private int stock;
}
