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
        em.persist(memberBasket);
    }

    public void joinProduct(BasketProduct basketProduct) {
        em.persist(basketProduct);
    }

    public String returnName() {
        return (String) em.createQuery("select name from Product p where p.name = :product_name")
                .getSingleResult();
    }

    public MemberBasket findById(String id) {
        return em.find(MemberBasket.class, id);
    }

    public BasketProduct findById2(String id) {
        return em.find(BasketProduct.class, id);
    }

//    public List findAll(MemberBasket memberBasket) {
//        return em.createQuery("select mb.products from MemberBasket mb", BasketProduct.class)
//                .setParameter("products", memberBasket.getProducts())
//                .getResultList();
//    }
}
