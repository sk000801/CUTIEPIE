package jpa.practice.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="members")
@Getter
@Setter
public class Member {

    private UUID uuid = UUID.randomUUID();

    @Id
    @Column(name="member_id")
    private String id = uuid.toString();

    private String pw;

    @Column(name="member_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;
}
