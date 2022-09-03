package jpa.practice.product;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Data
@Component
//@Entity
//@Table(name="product_image")
@RequiredArgsConstructor
public class ProductImage {

//    @Column(name="image_id")
//    @Id
//    private String image_id = UUID.randomUUID().toString();
//
    private final String fileDir = "C:/images/";

    public String getFullPath(String filename) {return fileDir+filename;}

    public ImageStore storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) return null;

        //작성자가 업로드한 파일 -> 저장시 바꿔진 파일명
        //파일명은 중복되지 않아야 하므로 UUID로 정해야함
        String originalFilename = file.getOriginalFilename();
        String storeFilename = UUID.randomUUID() +"."+extractExt(originalFilename);
        //파일 저장
        file.transferTo(new File(getFullPath(storeFilename)));
        return new ImageStore(originalFilename, storeFilename);
    }

    private String extractExt(String originalFilename) {
        //확장자 추출.. img 같은 칭구들 저장해주는
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos+1);
    }

}
