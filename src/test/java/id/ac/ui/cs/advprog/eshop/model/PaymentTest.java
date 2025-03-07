package id.ac.ui.cs.advprog.eshop.model;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Payment payment;
    HashMap<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
    }

    @Test
    void TestStatusSuccess() {
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void TestStatusFailed() {
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("INVALID_STATUS");
        });
    }

    @Test
    void TestValidVoucher() {
        paymentData.put("voucherCode", "ESHOP12345678MAP");
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        payment.setStatus("SUCCESS");
        payment.setPaymentMethod("ESHOP", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void TestInvalidVoucher() {
        paymentData.put("voucherCode", "ESHOPMAPP2345");
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        payment.setStatus("SUCCESS");
        payment.setPaymentMethod("ESHOP", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void TestValidDeliveryData() {
        paymentData.put("address", "Jl. Panglima Polim, Jakarta Selatan");
        paymentData.put("deliveryFee", "18500");
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        payment.setStatus("SUCCESS");
        payment.setPaymentMethod("ESHOP", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void TestInvalidAddress() {
        paymentData.put("address", null);
        paymentData.put("deliveryFee", "18500");
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        payment.setStatus("SUCCESS");
        payment.setPaymentMethod("ESHOP", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void TestInvalidDeliveryFee() {
        paymentData.put("address", "Jl. Panglima Polim, Jakarta Selatan");
        paymentData.put("deliveryFee", "");
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        payment.setStatus("SUCCESS");
        payment.setPaymentMethod("ESHOP", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
}
