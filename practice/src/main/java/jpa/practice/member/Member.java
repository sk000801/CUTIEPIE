package jpa.practice.member;

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
@Data
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
    @JoinColumn(name="account_id")
    private MemberAccount memberAccount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    private List<Order> PostOrder = new ArrayList<>();

    public static Member create(String name, MemberAccount memberAccount) {
        Member member = new Member();
        member.setName(name);
        member.setMemberAccount(memberAccount);
        return member;
    }

    public void addOrder(Order order) {
        PostOrder.add(order);
        order.setMember(this);
    }
    public void cancel(Order order) {
        PostOrder.remove(order);
    }
}
