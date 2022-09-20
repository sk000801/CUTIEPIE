package jpa.practice.controller;

import jpa.practice.form.ProductForm;
import jpa.practice.image.ImageRepository;
import jpa.practice.image.ImageStore;
import jpa.practice.image.ProductImage;
import jpa.practice.product.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.String;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProductController extends HttpServlet {
    private final ProductService productService;
    private final ImageRepository imageRepository;

    @PostMapping("/admins/pManage/join")
    public void join2(ProductForm form,
                      @RequestParam(value="file", required = false) MultipartFile file)
            throws IOException {
        Product product = new Product();
        product.setName(form.getPName());
        product.setPrice(form.getPrice());
        product.setStock(form.getStock());
        product.setCategory(form.getCategory());
        product.setDetail(form.getDetail());

        ProductImage productImage = new ProductImage();
        ImageStore imageStore = new ImageStore();

        String originalFilename = file.getOriginalFilename();
        String storeFilename = UUID.randomUUID() +"."+productImage.extractExt(originalFilename);
        imageStore.setStoreFilename(storeFilename);
        imageStore.setUploadFilename(originalFilename);
        imageStore.setData(file.getBytes());
        imageStore.setType(file.getContentType());

        //파일 저장
        file.transferTo(new File(productImage.getFullPath(storeFilename)));

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
        form.setDetail(product.getDetail());

        model.addAttribute("form", form);

        return "products/editProduct";
    }

    @PostMapping("/admins/pManage/{id}/edit")
    public void edit2(ProductForm form) {
        Product product = new Product();
        product.setName(form.getPName());
        product.setPrice(form.getPrice());
        product.setStock(form.getStock());
        product.setDetail(form.getDetail());

        productService.join(product);
        //return "redirect:/admins/pManage";
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
