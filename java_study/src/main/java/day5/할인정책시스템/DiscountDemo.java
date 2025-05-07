package day5.할인정책시스템;

public class DiscountDemo {
    public static void main(String[] args) {
        double originalPrice = 100000.0;

        PriceCalculator priceCalculator = new PriceCalculator();
        System.out.println("원래 상품 가격" + originalPrice);
        System.out.println("비율 할인(10%)");

        priceCalculator.setDiscountStrategy(new PercentageDiscount(10));
        double priceWithPercentage = priceCalculator.calculatePrice(originalPrice);
        System.out.println("점률 할인 적용 금액 : " + priceWithPercentage + "원");


        System.out.println("정액 할인 적용 만원");
        priceCalculator.setDiscountStrategy(new FixedAmountDiscount(10000));
        double priceWithFixedAmount = priceCalculator.calculatePrice(originalPrice);
        System.out.println("정액 할인된 금액" + priceWithFixedAmount+"원 입니다");

    }
}
