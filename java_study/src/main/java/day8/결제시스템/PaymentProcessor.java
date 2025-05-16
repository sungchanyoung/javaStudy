package day8.결제시스템;

public interface PaymentProcessor {
    void processPayment(double payment) throws PaymentValidationException;
}
