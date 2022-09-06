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
// 여러가지 상품들이 종합된 하나의 주문서
public class Order {

    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> lists;

//    private String pName;
//    private int pNumber;
//    굳이 필요하다는 생각이 안드는게 왜냐면 orderproduct로 각각의 상품들 정보가 들어오는데 얘가 필요?
    public void addOrderProduct(OrderProduct orderProduct) {
        lists.add(orderProduct);
        orderProduct.setOrder(this);
    }

    public void cancel() {
        for(OrderProduct orderProduct : lists) {
            orderProduct.cancel();
        }
    }
    public void add() {
        for(OrderProduct orderProduct : lists ) {
            orderProduct.addProduct();
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
