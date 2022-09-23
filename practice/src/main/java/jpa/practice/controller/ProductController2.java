package jpa.practice.controller;

import jpa.practice.form.ProductForm;
import jpa.practice.image.ImageStore;
import jpa.practice.product.Product;
import jpa.practice.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController2 {

    private final ProductService productService;
    private final ImageService imageService;

    //여기서 오류날 수 있는건 fetch를 시도할 때 LAZY로 설정을 하면 바로바로 들어가지 않아서
    //이렇게 json 형태로 page에 반환을 요청하면 요류가 나는듯해용...
    @ResponseBody
    @GetMapping(path="/products/list")
    public List<Product> list() {
        List<Product> lists = productService.findAll();

        return lists;
    }

    @PostMapping("/admins/pManage/join")
    public ResponseEntity<?> join2(ProductForm form, @RequestParam("file") MultipartFile file
                                   ,HttpServletResponse response)
            throws IOException {

        Product product = Product.create(form.getPName(), form.getStock(), form.getPrice(),
                form.getDetail(), form.getCategory());

//        String name = file.getName();
//        String ext = name.substring(name.lastIndexOf(".") + 1);

        ImageStore imageStore = new ImageStore();
//        imageStore.setIdExt(imageStore.getImage_id()+ext);

        if(file.isEmpty()) {
            response.sendError(404, "클라이언트 오류");
        }

        String downloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/products/image/")
                .path(imageStore.getImage_id())
                .toUriString();

        imageStore.setFileName(imageService.saveImage(file));
        imageStore.setUrl(downloadURI);
        product.setImageStore(imageStore);

        productService.join(product);

        return new ResponseEntity<>(downloadURI, HttpStatus.OK);
    }
}
