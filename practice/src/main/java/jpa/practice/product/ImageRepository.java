package jpa.practice.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Transactional
public class ImageRepository {
    private final EntityManager em;

    public void join(ImageStore imageStore) {
        em.persist(imageStore);
    }

}
