package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private int idCount = 1;

    @Override
    public Product create(Product product){
        productRepository.create(product);
        product.setProductId(String.format("%c%d%c",
                product.getProductName().charAt(0), idCount++,
                product.getProductName().charAt(product.getProductName().length() - 1)));
        return product;
    }

    @Override
    public List<Product> findAll(){
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product findProductById(String id){
        return productRepository.findProductById(id);
    }

    @Override
    public void updateProduct(Product updatedProduct) {
        productRepository.updateProduct(updatedProduct);
    }

    @Override
    public void deleteProduct(String id){
        productRepository.deleteProduct(id);
    }
}