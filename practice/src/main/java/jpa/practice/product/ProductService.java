package jpa.practice.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void join(Product product) {
        productRepository.join(product);
    }

    public Product findName(String name) {
        return productRepository.findName(name);
    }

    public List<Product> searchProduct(String name) {
        return productRepository.findProduct(name);
    }

}
