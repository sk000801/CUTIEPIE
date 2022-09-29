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

    @OneToMany(mappedBy="memberBasket", fetch = FetchType.EAGER)
    // cascade = CascadeType.PERSIST
    @JsonIgnore
    private List<BasketProduct> products = new ArrayList<>();

    public void addProducts(BasketProduct basketProduct) {
        products.add(basketProduct);
        basketProduct.setMemberBasket(this);
    }

    public static MemberBasket create(Member member, BasketProduct basketProduct) {
        MemberBasket memberBasket;
        if(!Objects.isNull(member.getMemberBasket())) {
            memberBasket = member.getMemberBasket();
        }
        else {
            memberBasket = new MemberBasket();
        }
        memberBasket.getProducts().add(basketProduct);

        return memberBasket;
    }

//        for(BasketProduct basketProduct : products) {
//            memberBasket.addProducts(basketProduct);
//        }
        //return memberBasket;


    public int total() {
        int total = 0;
        for(BasketProduct basketProduct : products) {
            total += basketProduct.totalPrice();
        }
        return total;
    }

}
