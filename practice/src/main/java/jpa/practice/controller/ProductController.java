package jpa.practice.controller;

import jpa.practice.image.ImageRepository;
import jpa.practice.image.ImageStore;
import jpa.practice.image.ProductImage;
import jpa.practice.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.String;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ImageRepository imageRepository;

//    @GetMapping("/admins/pManage")
//    public String page() {
//        return "products/productMain";
//    }
//
//    @GetMapping("/admins/pManage/join")
//    public String join(Model model) {
//        model.addAttribute("form", new ProductForm());
//        return "products/joinProduct";
//    }

    @PostMapping("/admins/pManage/join")
    public void join2(ProductForm form, @RequestParam(value="file", required = false) MultipartFile file) throws IOException {
        Product product = new Product();
        product.setName(form.getPName());
        product.setPrice(form.getPrice());
        product.setStock(form.getStock());

//        if (file.isEmpty()) return null; 경고창 띄우기


        ProductImage productImage = new ProductImage();
        ImageStore imageStore = new ImageStore();

        String originalFilename = file.getOriginalFilename();
        String storeFilename = UUID.randomUUID() +"."+productImage.extractExt(originalFilename);
        imageStore.setStoreFilename(storeFilename);
        imageStore.setUploadFilename(originalFilename);

                //파일 저장
        file.transferTo(new File(productImage.getFullPath(storeFilename)));

        //imageRepository.join(imageStore);

        product.setImageStore(imageStore);

        productService.join(product);
    }

    @GetMapping("/admins/pManage/{id}/edit")
    public String edit(@PathVariable("id") String id, Model model) {
        Product product = productService.findId(id);

        ProductForm form = new ProductForm();

        form.setPName(product.getName());
        form.setPrice(product.getPrice());
        form.setStock(product.getStock());

        model.addAttribute("form", form);

        return "products/editProduct";
    }

    @PostMapping("/admins/pManage/{id}/edit")
    public String edit2(ProductForm form) {
        Product product = new Product();
        product.setName(form.getPName());
        product.setPrice(form.getPrice());
        product.setStock(form.getStock());

        productService.join(product);
        return "redirect:/admins/pManage";
    }

    @GetMapping("/admins/pManage/search")
    public String search(@RequestParam(name="name", required = false) String name, Model model) {
        List<Product> results;
        if(name != null) {
            results = productService.findName(name);
        }
        else {
            results = productService.findAll();
        }
        model.addAttribute("members", results);
        return "products/productList";
    }

}
