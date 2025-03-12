package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData){
        Payment newPayment = new Payment(UUID.randomUUID().toString());

        HashMap<String, String> paymentDataCopy = new HashMap<>(paymentData);
        newPayment.setPaymentMethod(method, paymentDataCopy);

        paymentRepository.save(newPayment, order);
        return newPayment;
    }

    @Override
    public Payment setStatus(Payment payment, String status){
        payment.setStatus(status);

        Order getOrder = paymentRepository.getPaymentOrderMap().get(payment);
        if (status.equals("SUCCESS")) {
            getOrder.setStatus("SUCCESS");
        } else {
            getOrder.setStatus("FAILED");
        }

        return payment;
    }

    @Override
    public Payment getPayment(String paymentId){
        return paymentRepository.getPaymentById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments(){
        return paymentRepository.getAllPayments();
    }
}
