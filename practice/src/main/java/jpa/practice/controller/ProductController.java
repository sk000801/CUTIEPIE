package jpa.practice.controller;

import jpa.practice.form.ProductForm;
import jpa.practice.image.ImageRepository;
import jpa.practice.image.ImageService;
import jpa.practice.image.ImageStore;
import jpa.practice.product.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.String;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProductController extends HttpServlet {
    private final ProductService productService;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    @GetMapping("/products/image/{id}")
    public ResponseEntity<?> download(@PathVariable("id") String id, HttpServletRequest request)
        throws FileNotFoundException {

//        int idx = idExt.lastIndexOf(".");
//        String id = idExt.substring(0, idx);
        ImageStore imageStore = imageRepository.findById(id);
        Resource resource = imageService.loadFile(imageStore.getFileName());

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch(IOException ex) {
            log.info("파일 타입을 결정할 수 없습니다!");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\\\"\" + resource.getFilename() + \"\\\"")
                .body(resource);
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
