package jpa.practice.controller;

import jpa.practice.member.MemberStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    private String id;
    private String pw;
    @NotEmpty
    private String name;
    private MemberStatus status;
}
