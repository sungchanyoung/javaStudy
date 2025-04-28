package day2.기존;

import java.util.List;

//빌더 패턴 구현 예시
public class Order {
    private final String orderId;
    private final String customerName;
    private final List<String> items;
    private final boolean express;
    private final String paymentMethod;
    private final String specialInstructions;

    public Order(String orderId,
                 String customerName,
                 List<String> items,
                 boolean express,
                 String paymentMethod,
                 String specialInstructions
    ) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
        this.express = express;
        this.paymentMethod = paymentMethod;
        this.specialInstructions = specialInstructions;
    }
}
