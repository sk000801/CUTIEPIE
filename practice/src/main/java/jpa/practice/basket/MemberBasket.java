package jpa.practice.basket;

import com.fasterxml.jackson.annotation.*;
import jpa.practice.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class MemberBasket {

    @Id
    private String basket_id = UUID.randomUUID().toString();

    @OneToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy="memberBasket", cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JsonIgnoreProperties("memberBasket")
    List<BasketProduct> products = new ArrayList<>();

    public void addProducts(BasketProduct basketProduct) {
        products.add(basketProduct);
        basketProduct.setMemberBasket(this);
    }

    public static MemberBasket create(Member member, BasketProduct... products) {
        MemberBasket memberBasket = new MemberBasket();
        memberBasket.setMember(member);
        for(BasketProduct basketProduct : products) {
            memberBasket.addProducts(basketProduct);
        }
        return memberBasket;
    }

    public int total() {
        int total = 0;
        for(BasketProduct basketProduct : products) {
            total += basketProduct.totalPrice();
        }
        return total;
    }

}
