package jpa.practice.member;

import jpa.practice.basket.MemberBasket;
import jpa.practice.product.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="members")
@Data
public class Member {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name="member_id")
    private String memberId;

    @Column(name="pw")
    private String pw;

    @Column(name="name")
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

//    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToOne
    @JoinColumn(name="basket_id")
    private MemberBasket memberBasket;
//
//    private List<Product> likes;
}
