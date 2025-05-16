package day8.결제시스템;

public class PaymentSystemDemo {
    public static void main(String[] args) {
        DefaultPayment payment = new DefaultPayment(5000.0);
        ImprovedPaymentGateway toss = new TossPaymentGateway(payment);
        PaymentProcessor processor = new improvedCreditCardProcessor(payment,toss);

        try {
            processor.processPayment(10000.0);
            processor.processPayment(-10000.0);

        } catch (PaymentValidationException e) {
            System.err.println("결제 실패" + e.getMessage());
        }

        try {
            processor.processPayment(90000.0);

        } catch (PaymentValidationException e) {
            System.err.println("결제 실패" + e.getMessage());
        }

    }
}
