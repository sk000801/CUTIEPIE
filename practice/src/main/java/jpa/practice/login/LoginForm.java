package jpa.practice.login;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginForm {

    private String memberId;

    private String pw;
}
