package jpa.practice.controller;

import javax.validation.constraints.NotEmpty;

public class MemberForm {
    private Long id;
    private String pw;
    @NotEmpty
    private String name;
}
