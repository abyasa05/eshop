package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    private int idCount = 1;

    public Product create(Product product){
        productData.add(product);
        product.setProductId(setId(product.getProductName()));
        return product;
    }

    public String setId(String name){
        return String.format("%c%d%c", name.charAt(0), idCount++, name.charAt(name.length() - 1));
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product findProductById(String id){
        Product getProduct = null;
        for (Product product: productData){
            if (product.getProductId().equals(id)) getProduct = product;
        }
        return getProduct;
    }

    public void updateProduct(Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(updatedProduct.getProductId())) {
                productData.set(i, updatedProduct);
                return;
            }
        }
    }

    public void deleteProduct(String id){
        Product product = findProductById(id);
        if (product != null) productData.remove(product);
    }
}
