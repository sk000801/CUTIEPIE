package jpa.practice.product;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@Data
public class ProductImage {

    private final String fileDir = "C:/images/";

    public String getFullPath(String filename) {return fileDir+filename;}

//    public ImageStore storeFile(MultipartFile file) throws IOException {
//        if (file.isEmpty()) return null;
//
//        //작성자가 업로드한 파일 -> 저장시 바꿔진 파일명
//        //파일명은 중복되지 않아야 하므로 UUID로 정해야함
//        String originalFilename = file.getOriginalFilename();
//        String storeFilename = UUID.randomUUID() +"."+extractExt(originalFilename);
//        //파일 저장
//        file.transferTo(new File(getFullPath(storeFilename)));
//        return new ImageStore(store_id, originalFilename, storeFilename);
//    }

    public String extractExt(String originalFilename) {
        //확장자 추출.. img 같은 칭구들 저장해주는
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos+1);
    }

}
