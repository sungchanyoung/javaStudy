package day3.optional.상품제고시스템;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ProductInventoryService {
    private Map<String, Product> inventory = new ConcurrentHashMap<>();

    public Optional<Product> findProduct(String id){
        return Optional.ofNullable(inventory.get(id));
    }

    //주문을 처리하느 메서드
    public void processOrder(String productId, int quantity){
        findProduct(productId)
                .filter(product -> product.getStock() >= quantity)//제고 파악
                .ifPresentOrElse(product -> {
                    product.setStock(product.getStock() - quantity);
                    System.out.println("Order processed for product: " + productId);
                }, () ->{
                   throw new IllegalArgumentException("제고가 없습니다" + productId);
                        }
                );
    }
}
