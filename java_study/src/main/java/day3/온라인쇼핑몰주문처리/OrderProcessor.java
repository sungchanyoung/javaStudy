package day3.온라인쇼핑몰주문처리;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderProcessor {
    public static void main(String[] args) {
        // 샘플 주문 데이터 생성
        List<Order> orders = getOrders();
        if(Objects.isNull(orders) || orders.isEmpty()){
            throw new IllegalArgumentException("orders is null or empty. Please check your input data.");
        }

        // 1. 총 주문 금액 계산
        // flatMap 을 사용하셔서 모든 주문에 포함된 상품들을 하나의 스트림으로 변환 한후 가격 합계 계산
        //스트림 형태가 와야 sum을 사용할수 있다
        double orderTotal = orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.println("총 주문 금액: " + orderTotal + "원");

        // 2. 카테고리별 판매 금액 집계
        // flatMap, grouping by, summingDouble
        Map<String,Double> categoryTotal = getOrders().stream()
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.summingDouble(Product::getPrice)
                ));
       categoryTotal.forEach((k,v) -> System.out.println(k + " : " + v));


        // 3. 최근 24시간 내 주문 필터링
        //날짜를 변수로 뺴서 생각해보자 -minusDay(1)로
        List<Order> recentOrder = getOrders().stream()
                .filter(order -> order.getOrderDate().isAfter(LocalDateTime.now().minusHours(24)))
                .toList();
        recentOrder.forEach(System.out::println);

        // 4. 고객별-기준 주문 횟수 및 총 금액 집계 (추가 예제)
        Map<String, Double> customerOrder = getOrders().stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerName,
                        Collectors.summingDouble(order -> order.getProducts().stream()
                                .mapToDouble(Product::getPrice)
                                .sum()
                        )
                ));
        customerOrder.forEach((k,v) -> System.out.println(k + " : " + v));
    }
    private static List<Order> getOrders() {
        // 현재 시간과 이전 시간 데이터 준비
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterdayTime = now.minusDays(2);

        // 다양한 카테고리의 상품 생성
        Product laptop = new Product("노트북", 1_200_000, "전자제품");
        Product phone = new Product("스마트폰", 800_000, "전자제품");
        Product book = new Product("자바 프로그래밍", 30_000, "도서");
        Product chair = new Product("사무용 의자", 150_000, "가구");
        Product desk = new Product("책상", 200_000, "가구");

        // 여러 주문 생성
        return List.of(
                new Order("ORD001", "김철수", List.of(laptop, chair), now),
                new Order("ORD002", "이영희", List.of(phone, book), now),
                new Order("ORD003", "박지민", List.of(desk), yesterdayTime),
                new Order("ORD004", "김철수", List.of(book, book), now.minusHours(6))
        );
    }

}
