package day5.결제시스템;

/*
    전력 패턴에서 전략을 담당한다
    다양한 결제 방법들 이 구현해야 할 공통 인터페이스
 */
public interface PaymentStrategy {

    /*
        결제 처리 하느 메소드
        @param amount - 결제할 금액
     */
    void pay(int amount);
}
