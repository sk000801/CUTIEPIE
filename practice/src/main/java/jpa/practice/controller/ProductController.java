package jpa.practice.controller;

import jpa.practice.product.Product;
import jpa.practice.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String join2(ProductForm form) {
        Product product = new Product();
        product.setName(form.getPName());
        product.setPrice(form.getPrice());
        product.setStock(form.getStock());

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

}
