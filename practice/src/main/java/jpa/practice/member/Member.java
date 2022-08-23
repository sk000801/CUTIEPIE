package jpa.practice.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="members")
@Getter
@Setter
@Data
public class Member {

    private UUID uuid = UUID.randomUUID();

    @Id
    private String id = uuid.toString();

    @Column(name="member_id")
    private String memberId;

    private String pw;

    @Column(name="member_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;
}
