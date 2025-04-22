package 주문시스템불변객체.잘된구현;

import java.util.ArrayList;
import java.util.List;

public final class Order {
    private final String orderId;
    private final List<String> items;
    private final double totalPrice;

    public Order(String orderId, List<String> items, double totalPrice) {
        this.orderId = orderId;
        this.items = List.copyOf(items); //불변 리스트 생성
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<String> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Order addItem(String item) {
        List<String> newItems = new ArrayList<>(items);
        newItems.add(item);
        return new Order(orderId, newItems, recalculateAmount(newItems));
    }

    private double recalculateAmount(List<String> items) {
        return items.size() * 10.0;
    }
}
