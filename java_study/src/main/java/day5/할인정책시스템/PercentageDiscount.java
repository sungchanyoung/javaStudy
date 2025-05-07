package day5.할인정책시스템;

public class PercentageDiscount implements DiscountStrategy {
    //할인율을 받는 생성자 구성 검증

    private double percentage;

    public PercentageDiscount(double percentage) {
        if (percentage < 0 || percentage > 100){
            throw new IllegalArgumentException("할인율 0에서 100사이어야합니다");
        }
        this.percentage = percentage;
    }

    @Override
    public double setDiscountAmount(double discountAmount) {
        return discountAmount * (percentage/100);
    }

}
