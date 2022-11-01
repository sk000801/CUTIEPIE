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

    public Product findId(String id) {
        return productRepository.findId(id);
    }

    public List<Product> findName(String name) {
        return productRepository.findProduct(name);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findContainKeyword(String name) {
        return productRepository.findContainKeyword(name);
    }
}
