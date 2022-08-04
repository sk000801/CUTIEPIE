package jpa.practice.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="members")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String pw;

    @Column(name="member_name")
    private String name;
}
