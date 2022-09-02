package jpa.practice.controller;

import jpa.practice.product.Product;
import jpa.practice.product.ProductImage;
import jpa.practice.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.lang.String;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/admins/pManage")
    public String page() {
        return "products/productMain";
    }

    @GetMapping("/admins/pManage/join")
    public String join(Model model) {
        model.addAttribute("form", new ProductForm());
        return "products/joinProduct";
    }

    @PostMapping("/admins/pManage/join")
//    @RequestMapping(path="/admins/pManage/join", method=RequestMethod.POST)
    public String join2(ProductForm form, @RequestParam(value="file", required = false) MultipartFile file) throws IOException {
        Product product = new Product();
        product.setName(form.getPName());
        product.setPrice(form.getPrice());
        product.setStock(form.getStock());

        ProductImage productImage = new ProductImage();
        productImage.setFilename(form.getFilename());
        productImage.setOriginal_name(file.getOriginalFilename());

        product.setProductImage(productImage);
        //product.setFilename(form.getFilename());
        //product.setFile(form.getFile());

        String path = "D:/image/";

        String filename = path + file.getOriginalFilename();
        FileCopyUtils.copy(file.getBytes(), new File(filename));

        productService.join(product);
        return "redirect:/admins/pManage";
    }

    @GetMapping("/admins/pManage/{id}/edit")
    public String edit(@PathVariable("id") String id, Model model) {
        Product product = productService.findId(id);

        ProductForm form = new ProductForm();

        form.setId(product.getId());
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
