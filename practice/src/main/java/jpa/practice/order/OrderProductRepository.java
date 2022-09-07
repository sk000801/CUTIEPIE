package jpa.practice.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
@RequiredArgsConstructor
public class OrderProductRepository {

    private final EntityManager em;

    public void join(OrderProduct orderProduct) {
        em.merge(orderProduct);
        //나중에 orderProduct 수정할 일 생기면 merge로 바꿔주자
    }
}
