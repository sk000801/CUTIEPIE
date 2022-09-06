package jpa.practice.order;

import jpa.practice.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class OrderRepository {

    private final EntityManager em;
    private final SessionManager sm;

    public void join(Order order) {
        em.persist(order);
    }

    public Order findId(String id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order as o")
                .getResultList();
    }

    public void delete(Order order) {
        em.remove(order);
    }
}
