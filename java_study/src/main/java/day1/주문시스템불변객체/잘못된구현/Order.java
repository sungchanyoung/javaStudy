package day1.주문시스템불변객체.잘못된구현;

import java.util.List;

public class Order {
    private String orderId;
    private List<String> items;
    private double totalPrice;

    public Order(String orderId, List<String> items, double totalPrice) {
        this.orderId = orderId;
        this.items = items; //외부 리스트 직접 참조
        this.totalPrice = totalPrice;
    }

    public void addItem(String item) {
        items.add(item);
        recalculateAmount();
    }
    public List<String> getItems() {
        return items;
    }
    public void recalculateAmount() {
        //급
    }
}
