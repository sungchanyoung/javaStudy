package day2.예제4;

public class PaymentFactory {
    public static  Payment createPayment(PaymentMethod method){
        return switch (method){
            case CREDIT_CARD -> new CreditCardPayment();
            case SAMSUNG_PAY -> new SamSungPassPayment();
            default -> throw new IllegalArgumentException("Unsupported payment method");
        };
    }
}
