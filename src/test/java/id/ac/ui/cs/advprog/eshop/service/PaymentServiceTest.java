package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void testAddPayment() {
        Order order = new Order("ID-Order1", List.of(new Product()), 1708570000L, "User 1");
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678MAP");

        Payment payment = new Payment("ID-Payment1");
        when(paymentRepository.save(any(Payment.class), eq(order))).thenReturn(payment);

        Payment addedPayment = paymentService.addPayment(order, "VOUCHER", paymentData);

        assertNotNull(addedPayment);
        assertEquals("VOUCHER", addedPayment.getMethod());
        verify(paymentRepository, times(1)).save(any(Payment.class), eq(order));
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("ID-Payment2");
        paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("ID-Payment3");
        paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void TestGetPaymentIfIdFound() {
        Payment payment = new Payment("ID-Payment4");
        when(paymentRepository.getPaymentById("ID-Payment4")).thenReturn(payment);

        Payment foundPayment = paymentService.getPayment("ID-Payment4");

        assertNotNull(foundPayment);
        assertEquals("ID-Payment4", foundPayment.getId());
        verify(paymentRepository, times(1)).getPaymentById("ID-Payment4");
    }

    @Test
    void TestGetPaymentIfIdNotFound() {
        when(paymentRepository.getPaymentById("zzz")).thenReturn(null);
        assertNull(paymentService.getPayment("zzz"));
    }

    @Test
    void getAllPayments_ShouldReturnListOfPayments() {
        List<Payment> payments = List.of(new Payment("ID-Payment5"), new Payment("ID-Payment6"));
        when(paymentRepository.getAllPayments()).thenReturn(payments);

        List<Payment> retrievedPayments = paymentService.getAllPayments();

        assertEquals(2, retrievedPayments.size());
        verify(paymentRepository, times(1)).getAllPayments();
    }
}
