package id.ac.ui.cs.advprog.eshop.model;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id){
        this.id = id;
    }

    public void setPaymentMethod(String method, HashMap<String, String> paymentData){
        if (!PaymentMethod.contains(method)){
            throw new IllegalArgumentException("Invalid method");
        } else {
            if (method.equals("VOUCHER")){
                this.method = method;
                this.paymentData = paymentData;

                if (!VoucherValidator.isValidVoucher(paymentData.get("voucherCode"))){
                    this.setStatus("REJECTED");
                }

            } else if (method.equals("CASH_ON_DELIVERY")) {
                this.method = method;
                this.paymentData = paymentData;

                if (paymentData.get("address") == null || paymentData.get("address").isEmpty()
                        || paymentData.get("deliveryFee") == null || paymentData.get("deliveryFee").isEmpty()) {
                    this.setStatus("REJECTED");
                }
            }
        }
    }

    public void setStatus(String status){
        if (!PaymentStatus.contains(status)){
            throw new IllegalArgumentException("Invalid status");
        } else {
            this.status = status;
        }
    }
}

class VoucherValidator {
    static boolean isValidVoucher(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != 16) {
            return false;
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }

        // Verify numeric characters count
        int digitCount = 0;
        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            }
        }

        return digitCount == 8;
    }
}
