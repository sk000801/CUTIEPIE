package jpa.practice.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductForm {

    private String id;

    //private Member member;

    private int price;

    private int count;
}
