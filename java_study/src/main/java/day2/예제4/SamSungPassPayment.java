package day2.예제4;

import java.math.BigDecimal;

public class SamSungPassPayment  implements Payment{

    private PaymentStatus status = PaymentStatus.PENDING;

    @Override
    public void processPayment(BigDecimal amount) {
        if(amount.equals(0)){
            status = PaymentStatus.CANCELED;
            throw new IllegalArgumentException("잔액 부족" + amount);
        }

        status = PaymentStatus.COMPLETED;
    }

    @Override
    public PaymentStatus getStatus() {
        return status;
    }
}
