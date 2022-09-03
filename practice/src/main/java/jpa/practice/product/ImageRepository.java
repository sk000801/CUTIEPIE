package jpa.practice.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.awt.*;

@Repository
@RequiredArgsConstructor
@Transactional
public class ImageRepository {
    private final EntityManager em;

    public void join(ImageStore imageStore) {
        em.persist(imageStore);
    }
}
