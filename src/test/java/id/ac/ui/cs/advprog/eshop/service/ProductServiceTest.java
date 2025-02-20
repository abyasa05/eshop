package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductName("New stuff");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        Product createdProduct = productService.create(product);
        assertNotNull(createdProduct);
        assertEquals(product.getProductName(), createdProduct.getProductName());
        assertNotEquals(11, createdProduct.getProductQuantity());
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> products = productService.findAll();
        assertNotNull(products);
        assertEquals(product, products.get(0));
        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
        assertNotEquals("Stuff", products.get(0).getProductName());
    }

    @Test
    void testFindProductById() {
        when(productRepository.findProductById(product.getProductId())).thenReturn(product);

        Product result = productService.findProductById(product.getProductId());
        Product result2 = productService.findProductById("N3f");

        assertEquals(product, result);
        assertNull(result2);
        assertEquals(product.getProductId(), result.getProductId());
    }

    @Test
    void testUpdateProduct() {
        doNothing().when(productRepository).updateProduct(any(Product.class));

        product.setProductName("Updated stuff");
        product.setProductQuantity(5);
        productService.updateProduct(product);

        // Verify if updateProduct method is called
        verify(productRepository, times(1)).updateProduct(product);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteProduct(product.getProductId());

        productService.deleteProduct(product.getProductId());
        verify(productRepository, times(1)).deleteProduct(product.getProductId());
    }
}
