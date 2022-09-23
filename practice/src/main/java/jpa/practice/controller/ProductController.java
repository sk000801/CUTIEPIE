package jpa.practice.controller;

import jpa.practice.form.ProductForm;
import jpa.practice.image.ImageRepository;
import jpa.practice.image.ImageStore;
import jpa.practice.product.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.lang.String;
import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProductController extends HttpServlet {
    private final ProductService productService;

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
