package jpa.practice.controller;

import jpa.practice.product.Product;
import jpa.practice.product.ProductService;
import lombok.RequiredArgsConstructor;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> Stashed changes

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController2 {

    private final ProductService productService;

    @ResponseBody
<<<<<<< Updated upstream
    @GetMapping(path="/products/list")
=======
    @RequestMapping(method = RequestMethod.GET, path = "/products/list")
>>>>>>> Stashed changes
    public List<Product> list() {
        List<Product> lists = productService.findAll();

        return lists;
    }
}
