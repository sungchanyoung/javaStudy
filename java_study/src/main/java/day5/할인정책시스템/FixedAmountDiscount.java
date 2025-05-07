package day5.할인정책시스템;

import java.util.Objects;

public class FixedAmountDiscount implements DiscountStrategy{

    private double fixedAmount;

    public FixedAmountDiscount(double fixedAmount) {
        if (fixedAmount < 0){
            throw new IllegalArgumentException("할인 금액은 0 이상이어야 합니다");
        }
        this.fixedAmount = fixedAmount;
    }


    //할인금액은 물건 금액보다 크면 안된다
    @Override
    public double setDiscountAmount(double discountAmount) {
        return Math.min(fixedAmount, discountAmount);
    }
}
