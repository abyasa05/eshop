package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    public Product findProductById(String id);
    public void updateProduct(Product updatedProduct);
    public void deleteProduct(String id);
}