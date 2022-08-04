package jpa.practice.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    private String pw;
    @NotEmpty
    private String name;
}
