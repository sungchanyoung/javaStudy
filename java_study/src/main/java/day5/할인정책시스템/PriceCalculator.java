package day5.할인정책시스템;

public class PriceCalculator {
    //최종적으로 계산
    //할인 전략이 설정되지 않은 경우 여외 처리
    //할인 금액 계산
    //최종 가격 = 원래 가격 - 할인 금액

    private DiscountStrategy discountStrategy;

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculatePrice(double originalPrice){

        if (discountStrategy == null){
            throw new IllegalArgumentException("할인전략이 설정되지 않았습니다");
        }

        double discountAmount = discountStrategy.setDiscountAmount(originalPrice);

        return originalPrice - discountAmount;
    }
}
