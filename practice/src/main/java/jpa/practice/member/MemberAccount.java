package jpa.practice.member;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
@Data
public class MemberAccount {

    @JoinColumn(name="id")
    private Member member;

    private String id;

    private String pw;

    public String security(String pw) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    }
}
