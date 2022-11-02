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

    @PostMapping("/admins/pManage/{id}/edit")
    public void edit2(ProductForm form, @PathVariable("id") String id) {
        Product product = productService.findId(id);
        product.setName(form.getPName());
        product.setPrice(form.getPrice());
        product.setStock(form.getStock());
        product.setDetail(form.getDetail());

        productService.join(product);
    }

    @GetMapping("/admins/pManage/search")
    public List<Product> search(@RequestParam(name="name", required = false) String name, Model model) {
        List<Product> results = null;
        if(name != null) {
            results = productService.findContainKeyword(name);
        }
        else {
            System.out.println("죄송합니다! 일치하는 상품이 하나도 없습니다! 다시 입력해 주세요!");
        }
        return results;
    }
    //이 친구는 제대로 된 검색을 구현하고 써봐야할듯..?

}
