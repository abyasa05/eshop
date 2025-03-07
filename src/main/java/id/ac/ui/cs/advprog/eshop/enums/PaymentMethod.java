package id.ac.ui.cs.advprog.eshop.enums;
import lombok.Getter;

@Getter
public enum PaymentMethod {
    VOUCHER("VOUCHER"),
    CASH_ON_DELIVERY("CASH_ON_DELIVERY");

    private final String method;

    private PaymentMethod(String method) {
        this.method = method;
    }

    public static boolean contains(String param) {
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if (paymentMethod.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
