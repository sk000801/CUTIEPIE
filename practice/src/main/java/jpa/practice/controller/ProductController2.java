package jpa.practice.controller;

import jpa.practice.product.Product;
import jpa.practice.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController2 {

    private final ProductService productService;

    //여기서 오류날 수 있는건 fetch를 시도할 때 LAZY로 설정을 하면 바로바로 들어가지 않아서
    //이렇게 json 형태로 page에 반환을 요청하면 요류가 나는듯해용...
    @ResponseBody
    @GetMapping(path="/products/list")
    public List<Product> list() {
        List<Product> lists = productService.findAll();

        return lists;
    }
}
