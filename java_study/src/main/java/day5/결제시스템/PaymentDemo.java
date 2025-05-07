package day5.결제시스템;

public class PaymentDemo {
    //전략은 - 결제 방법
    public static void main(String[] args) {

        PaymentStrategy creditCardPayment = new CreditCardPayment("홍기롱","1234-1234-1234-1234","1237", "12/34");

        PaymentProcessor paymentProcessor = new PaymentProcessor(creditCardPayment);

        System.out.println("------신용 카드 ------");
        paymentProcessor.pay(10000);

        //결제 방법을 payPal 변경
        System.out.println("------PayPal ------");
        PaymentStrategy paypalPayment = new PayPalPayment("<EMAIL","password");
        paymentProcessor.setPaymentStrategy(paypalPayment);
        paymentProcessor.pay(10000); //공통된 알고리즘

        //kakao
        System.out.println("---카카오페이 ---");
        PaymentStrategy kakaoPayPayment = new KaKaoPayPayment("010-1234-1234","password");
        paymentProcessor.setPaymentStrategy(kakaoPayPayment);
        paymentProcessor.pay(10000);
    }
}
