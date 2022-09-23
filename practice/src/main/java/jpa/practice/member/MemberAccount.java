package jpa.practice.member;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Table(name="memberAccount")
public class MemberAccount {

    @Id
    private String id;

    private String pw;

//    public String push(String pw) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder.encode(pw);
//    }
}
