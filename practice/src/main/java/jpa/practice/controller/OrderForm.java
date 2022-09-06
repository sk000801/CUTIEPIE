package jpa.practice.controller;

import jpa.practice.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm {

    private String id;

    //private Member member;

    private String pName;

    private int pNumber;
}
