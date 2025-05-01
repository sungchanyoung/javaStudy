package day3.optional.상품제고시스템;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Try {
    //ConcurrentHashMap :동시성을 높이기 위한 나온 클래스 HashMap보다 안전성이 높다
    private Map<String, Product> inventory = new ConcurrentHashMap<>();

    public Optional<Product> findProduct(String id){
        return Optional.ofNullable(inventory.get(id));
    }

    //주문 처리하는 메서드
    //ifPresentOrElse :if-else문이랑 비슷하다고 보연된다
    public void processOrder(String productId, int quantity){
        findProduct(productId)
                .filter(product -> product.getStock() >=quantity)
                .ifPresentOrElse(product -> {
                    product.setStock(product.getStock()- quantity);
                    System.out.println("Order processed for product: " + productId);
                },() ->{
                    throw  new IllegalArgumentException("재고 업습니다 "+productId);
                });

    }
}
