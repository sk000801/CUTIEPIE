package jpa.practice.member;

import jpa.practice.product.BascketProduct;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class MemberBascket {

    @Id
    private String baskcet_id = UUID.randomUUID().toString();

    @OneToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy="memberBascket")
    List<BascketProduct> products = new ArrayList<>();

    public static MemberBascket create(Member member) {
        MemberBascket memberBascket = new MemberBascket();
        memberBascket.setMember(member);
        return memberBascket;
    }

}
