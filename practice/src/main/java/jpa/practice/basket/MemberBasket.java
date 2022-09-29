package jpa.practice.basket;

import com.fasterxml.jackson.annotation.*;
import jpa.practice.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class MemberBasket {

    @Id
    @Column(name="basket_id")
    private String basket_id = UUID.randomUUID().toString();

    @OneToMany(mappedBy="memberBasket", fetch = FetchType.LAZY)
    // cascade = CascadeType.PERSIST
    @JsonIgnore
    List<BasketProduct> products = new ArrayList<>();

    public void addProducts(BasketProduct basketProduct) {
        products.add(basketProduct);
        basketProduct.setMemberBasket(this);
    }

    public static MemberBasket create(Member member, BasketProduct... products) {
        MemberBasket memberBasket;

        if(!Objects.isNull(member.getMemberBasket())) {
            memberBasket = member.getMemberBasket();
        }
        else {
            memberBasket = new MemberBasket();
        }

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
