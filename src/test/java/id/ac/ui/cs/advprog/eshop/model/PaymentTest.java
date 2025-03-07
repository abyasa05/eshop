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
    void testStatusSuccess() {
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testStatusFailed() {
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("INVALID_STATUS");
        });
    }

    @Test
    void testValidVoucher() {
        paymentData.put("voucherCode", "ESHOP12345678MAP");
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        payment.setStatus("SUCCESS");
        payment.setPaymentMethod("VOUCHER", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testInvalidVoucher() {
        paymentData.put("voucherCode", "ESHOPMAPP2345");
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        payment.setStatus("SUCCESS");
        payment.setPaymentMethod("VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testValidDeliveryData() {
        paymentData.put("address", "Jl. Panglima Polim, Jakarta Selatan");
        paymentData.put("deliveryFee", "18500");
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        payment.setStatus("SUCCESS");
        payment.setPaymentMethod("CASH_ON_DELIVERY", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testInvalidAddress() {
        paymentData.put("address", null);
        paymentData.put("deliveryFee", "18500");
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        payment.setStatus("SUCCESS");
        payment.setPaymentMethod("CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testInvalidDeliveryFee() {
        paymentData.put("address", "Jl. Panglima Polim, Jakarta Selatan");
        paymentData.put("deliveryFee", "");
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        payment.setStatus("SUCCESS");
        payment.setPaymentMethod("CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testInvalidPaymentMethod() {
        paymentData.put("address", "Jl. Panglima Polim, Jakarta Selatan");
        paymentData.put("deliveryFee", "");
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b");
        payment.setStatus("SUCCESS");

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentMethod("INVALID_METHOD", paymentData);
        });
    }
}
