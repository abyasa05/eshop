package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import java.util.List;
import java.util.ArrayList;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {

    PaymentRepository paymentRepository;
    List<Payment> paymentList;
    List<Order> orderList;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        Order order1 = new Order("Test-Id-01",
                productList, 1708560000L, "User 1");
        Order order2 = new Order("Test-Id-02",
                productList, 1708560000L, "Oser 2");
        Order order3 = new Order("Test-Id-03",
                productList, 1708560000L, "User 3");

        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        Payment payment2 = new Payment("39246701-954y-357e-901w-39s0j28106a0");
        Payment payment3 = new Payment("72958137-829d-84r5-03r5-201o4r6735t6");
        payment3.setStatus("SUCCESS");

        paymentList = new ArrayList<>();
        paymentList.add(payment1);
        paymentList.add(payment2);
        paymentList.add(payment3);

        orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
    }

    @Test
    void testSave() {
        paymentRepository.save(paymentList.get(0), orderList.get(0));
        paymentRepository.save(paymentList.get(1), orderList.get(0));

        Payment getPayment1 = paymentRepository.getAllPayments().get(0);
        Payment getPayment2 = paymentRepository.getAllPayments().get(1);
        assertEquals(orderList.get(0), paymentRepository.getPaymentOrderMap().get(getPayment1));
        assertEquals(paymentList.get(1).getId(), getPayment2.getId());
    }

    @Test
    void TestFindByIdIfFound() {
        paymentRepository.save(paymentList.get(2), orderList.get(2));

        Payment findResult = paymentRepository.getPaymentById(paymentList.get(2).getId());
        assertEquals(orderList.get(2), paymentRepository.getPaymentOrderMap().get(findResult));
        assertEquals("72958137-829d-84r5-03r5-201o4r6735t6", findResult.getId());
        assertEquals("SUCCESS", findResult.getStatus());
    }

    @Test
    void TestFindByIdIfNotFound() {
        paymentRepository.save(paymentList.get(2), orderList.get(2));

        Payment findResult = paymentRepository.getPaymentById(paymentList.get(0).getId());
        assertNull(findResult);
    }
}
