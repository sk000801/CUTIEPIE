package jpa.practice.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductForm {

    private String id;

    private int price;

    private int count;
}
