package jpa.practice.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpa.practice.basket.MemberBasket;
import jpa.practice.order.Order;
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
@Getter
@Setter
public class Member {

    @Id
    @Column(name="id")
    private String id = UUID.randomUUID().toString();

    @Column(name="name")
    private String name;

    @OneToOne
    @JoinColumn(name="basket_id")
    private MemberBasket memberBasket;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="uuid")
    private MemberAccount memberAccount;

    public static Member create(String name, MemberAccount memberAccount) {
        Member member = new Member();
        member.setName(name);
        member.setMemberAccount(memberAccount);
        return member;
    }

}
