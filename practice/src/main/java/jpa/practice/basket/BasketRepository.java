package jpa.practice.basket;

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
        em.persist(memberBasket);
    }

    public void joinProduct(BasketProduct basketProduct) {
        em.persist(basketProduct);
    }

    public List<MemberBasket> findAll() {
        return em.createQuery("select member_basket from MemberBasket as member_basket", MemberBasket.class)
                .getResultList();
    }
}
