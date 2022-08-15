package jpa.practice.product;

import jpa.practice.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ProductRepository {
    private final EntityManager em;

    public void join(Product product) {
        em.persist(product);
    }

    public Product findName(String name) {
        return em.createQuery("select p from Product p where p.name = :name", Product.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public Product findId(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findProduct(String name) {
        return em.createQuery("select p from Product p where p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Product> findAll() {
        return em.createQuery("select p from Product as p", Product.class)
                .getResultList();
    }
}
