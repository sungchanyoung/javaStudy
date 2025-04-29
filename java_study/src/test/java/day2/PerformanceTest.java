package day2;

import day2.예제1.CreditCardPayment;
import day2.예제1.Payment;
import day2.예제1.PaymentFactory;
import day2.예제1.PaymentMethod;
import org.junit.jupiter.api.Test;

public class PerformanceTest {

    @Test
    void factoryPerformance(){
        //1.직접 인스턴스 생성하는 방식
        long start = System.nanoTime();
        for(int i = 0; i < 1000000; i++) {
            Payment payment = new CreditCardPayment();
        }
        long directTime = System.nanoTime() - start;

        //팩토리 패턴을 활용한 생성
        start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            Payment payment = PaymentFactory.createpayment(PaymentMethod.CREDIT_CARD);

        }
        long factoryTime = System.nanoTime() - start;
        System.out.println("Direct instances time = %d ms%n" + directTime/1000000 + "ms");
        System.out.println("Factory instances time = %d ms%n" + factoryTime/1000000 + "ms");
    }
}
