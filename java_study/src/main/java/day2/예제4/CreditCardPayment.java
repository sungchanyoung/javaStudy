package day2.예제4;

import java.math.BigDecimal;

public class CreditCardPayment implements Payment{
    private PaymentStatus status = PaymentStatus.PENDING;

    @Override
    public void processPayment(BigDecimal amount) {
        if(amount.equals(0)){
            status = PaymentStatus.CANCELED;
        }
        status = PaymentStatus.COMPLETED;
    }

    @Override
    public PaymentStatus getStatus() {
        return status;
    }
}
