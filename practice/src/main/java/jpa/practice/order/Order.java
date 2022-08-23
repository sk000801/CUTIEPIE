package jpa.practice.order;

import jpa.practice.member.Member;
import jpa.practice.product.Product;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="orders")
public class Order {

    private UUID uuid = UUID.randomUUID();

    @Id
    @Column(name="order_id")
    private String id = uuid.toString();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> lists;

    private String pName;
    private int pNumber;

}
