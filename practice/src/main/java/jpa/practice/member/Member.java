package jpa.practice.member;

import jpa.practice.product.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="members")
@Getter
@Setter
@Data
public class Member {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name="member_id")
    private String memberId;

    private String pw;

    @Column(name="name")
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private List<Product> bascket;

    private List<Product> likes;
}
