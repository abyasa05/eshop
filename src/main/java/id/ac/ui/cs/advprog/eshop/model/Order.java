package id.ac.ui.cs.advprog.eshop.model;
//import java.util.UUID;
import java.util.List;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;

@Builder
@Getter @Setter
public class Order {
    String id;
    List<Product> products;
    Long orderTime;
    String author;
    String status;

    public Order(String id, List<Product> products, Long orderTime, String author) {

    }

    public Order(String id, List<Product> products, Long orderTime, String author, String status) {

    }
}
