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

    public Payment save(Payment payment, Order order) {
        allPayments.add(payment);
        paymentOrderMap.put(payment, order);

        return payment;
    }

    public Payment getPaymentById(String id){
        for (Payment payment : allPayments) {
            if (payment.getId().equals(id)) {
                return payment;
            }
        }

        return null;
    }
}
