package jpa.practice.basket;

import jpa.practice.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class BasketRepository {

    private final EntityManager em;

    public void joinAll(MemberBasket memberBasket) {
        em.merge(memberBasket);
    }

    public void joinProduct(BasketProduct basketProduct) {
        em.merge(basketProduct);
    }

    public MemberBasket findById(String id) {
        return em.find(MemberBasket.class, id);
    }

    public BasketProduct findById2(String id) {
        return em.find(BasketProduct.class, id);
    }

}
