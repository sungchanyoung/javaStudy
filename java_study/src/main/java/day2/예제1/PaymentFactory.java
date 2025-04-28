package day2.예제1;


//심플 팩토리
public class PaymentFactory {
    public static Payment createpayment(PaymentMethod method){
        return switch (method){
            case CREDIT_CARD -> new CreditCardPayment();
            case KAKAO_PAY -> new KaKaoPayment();
            default -> throw new IllegalArgumentException("Unsupported payment method:");

        };

    }
}
