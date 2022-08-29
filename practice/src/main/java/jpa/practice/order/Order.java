package jpa.practice.order;

import jpa.practice.member.Member;
import jpa.practice.product.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import java.sql.*;

@Entity
@Table(name="orders")
@Data
public class Order {

    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> lists;

    private String pName;
    private int pNumber;

}
