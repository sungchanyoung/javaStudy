package day2.예제4;

import java.math.BigDecimal;

enum PaymentStatus{
    COMPLETED("결제 완료"),
    PENDING("결제중"),
    CANCELED("취소");

    private final String description;

    private PaymentStatus(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}

public interface Payment {
    void processPayment(BigDecimal amount);
    PaymentStatus getStatus();
}
