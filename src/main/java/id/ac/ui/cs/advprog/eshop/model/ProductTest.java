package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.*;
public class ProductTest {
    Product product;
    
    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eBS5Be9f-1c39-4d0e-8860-7lafeaf63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals("eBS5Be9f-1c39-4d0e-8860-7lafeaf63bd6", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }
}
