package jpa.practice.member;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
public class MemberAccount {

    private String id;

    private String pw;

    public String push(String pw) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pw);
    }
}
