package jpa.practice.controller;

import jpa.practice.member.MemberStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty
    private String id;
    @NotEmpty
    private String pw;
    @NotEmpty
    private String name;
    private MemberStatus status;
}
