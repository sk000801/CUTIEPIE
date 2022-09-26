package jpa.practice.member;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@Table(name="memberAccount")
public class MemberAccount {

    @Id
    private String uuid = UUID.randomUUID().toString();

    @Column(name="accountId")
    private String id;

    private String pw;

    public static MemberAccount create(String id, String pw) {
        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setId(id);
        memberAccount.setPw(pw);
        return memberAccount;
    }
//    public String push(String pw) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder.encode(pw);
//    }
}
