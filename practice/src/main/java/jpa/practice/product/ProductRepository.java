package jpa.practice.product;

import jpa.practice.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
@Transactional
public class ProductRepository {
    private final EntityManager em;

    public void join(Product product) {
        em.merge(product);
    }

    public Product findName(String name) {
        return em.createQuery("select p from Product p where p.name = :name", Product.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public Product findId(String id) {
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

    public List<Product> findContainKeyword(String name) {
        Product[] findResult = (Product[]) findAll().stream().filter(product -> product.getName().contains(name)).toArray();
        List<Product> findResults = Arrays.asList(findResult);
        return findResults;
    }
}
