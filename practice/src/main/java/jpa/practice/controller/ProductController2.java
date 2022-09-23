package jpa.practice.controller;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import jpa.practice.form.ProductForm;
import jpa.practice.image.ImageRepository;
import jpa.practice.image.ImageStore;
import jpa.practice.product.Product;
import jpa.practice.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController2 {

    private final ProductService productService;
    private final ImageRepository imageRepository;
    private static Storage storage = StorageOptions.getDefaultInstance().getService();

    //여기서 오류날 수 있는건 fetch를 시도할 때 LAZY로 설정을 하면 바로바로 들어가지 않아서
    //이렇게 json 형태로 page에 반환을 요청하면 요류가 나는듯해용...
    @ResponseBody
    @GetMapping(path="/products/list")
    public List<Product> list() {
        List<Product> lists = productService.findAll();

        return lists;
    }

    @PostMapping("/admins/pManage/join")
    public String join2(ProductForm form, @RequestParam("file") MultipartFile file
                                   , HttpServletResponse response)
            throws IOException {

        if(file.isEmpty()) {
            response.sendError(404, "클라이언트 오류");
        }

        Product product = Product.create(form.getPName(), form.getStock(), form.getPrice(),
                form.getDetail(), form.getCategory());

        ImageStore imageStore = new ImageStore();

        try {
            BlobInfo blobinfo  = storage.create(
                    BlobInfo.newBuilder("cutiepie_image", imageStore.getImage_id())
                            .build(), file.getBytes(),
                    Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ)
            );
            imageStore.setUrl(blobinfo.getMediaLink());

            product.setImageStore(imageStore);
            productService.join(product);

            return imageStore.getUrl();
        } catch(IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/products/image/{id}")
    @ResponseBody
    public String download(@PathVariable("id") String id, HttpServletRequest request) {
        ImageStore imageStore = imageRepository.findById(id);
        return imageStore.getUrl();
    }
}
