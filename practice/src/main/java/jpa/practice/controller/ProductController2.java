package jpa.practice.controller;

import jpa.practice.product.Product;
import jpa.practice.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController2 {

    private final ProductService productService;

    @ResponseBody
    @GetMapping(path="/products/list")
    public List<Product> list() {
        List<Product> lists = productService.findAll();

        return lists;
    }
}
