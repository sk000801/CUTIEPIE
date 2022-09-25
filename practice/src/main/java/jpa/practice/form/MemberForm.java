package jpa.practice.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Data
public class MemberForm {
    @NotEmpty
    private String memberId;
    @NotEmpty
    private String pw;
    @NotEmpty
    private String name;
    private MemberStatus status;
}
