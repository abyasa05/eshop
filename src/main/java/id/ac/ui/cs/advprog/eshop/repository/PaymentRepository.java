package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import lombok.Getter;

@Getter
public class PaymentRepository {

    private List<Payment> allPayments = new ArrayList<>();
    private Map<Payment, Order> paymentOrderMap = new HashMap<>();

    public Payment save(Payment payment, Order order) { return null; }

    public Payment getPaymentById(String id){ return null ; }
}
