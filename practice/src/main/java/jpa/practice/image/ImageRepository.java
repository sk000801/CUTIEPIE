package jpa.practice.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional
public class ImageRepository {
    private final EntityManager em;

    public void join(ImageStore imageStore) {
        em.persist(imageStore);
    }

    public ImageStore findById(String id) {
        return em.find(ImageStore.class, id);
    }

}
