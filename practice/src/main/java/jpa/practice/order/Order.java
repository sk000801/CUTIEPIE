package jpa.practice.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpa.practice.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.sql.*;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
// 여러가지 상품들이 종합된 하나의 주문서
public class Order {
    @Id
    @Column(name="order_id")
    @JsonIgnore
    private String order_id = UUID.randomUUID().toString();

    @JsonIgnore
    private String memberId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> lists = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdDate;

    public void addOrderProduct(OrderProduct orderProduct) {
        lists.add(orderProduct);
        orderProduct.setOrder(this);
    }

    public static Order create_single(Member member, OrderProduct orderProduct) {
        Order order = new Order();
        order.setMemberId(member.getId());
        order.addOrderProduct(orderProduct);
        return order;
    }
    public static Order create_basket(Member member, List<OrderProduct> list) {
        Order order = new Order();
        order.setMemberId(member.getId());
        for(OrderProduct orderProduct : list) {
           order.addOrderProduct(orderProduct);
        }
        return order;
    }

    public void cancel() {
        for(OrderProduct orderProduct : lists) {
            orderProduct.cancel();
        }
    } //주문을 취소할 땐 이 친구를 써먹으면 그냥 되나...?
    public void add() {
        for(OrderProduct orderProduct : lists) {
            orderProduct.add();
        }
    }
    public int totalPrice() {
        int total=0;
        for(OrderProduct orderProduct : lists) {
            total += orderProduct.total();
        }
        return total;
    }


}
