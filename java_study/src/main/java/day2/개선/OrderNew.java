package day2.개선;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//빌더 어노테이션을 쓰면 어떻게 되는지
public class OrderNew {
    private final String orderId;
    private final String customerName;
    private final List<String> items;
    private final boolean express;
    private final String paymentMethod;
    private final String deliveryAddress;
    private final String specialInstructions;

    private OrderNew(Builder builder) {
        this.orderId = builder.orderId;
        this.customerName = builder.customerName;
        this.items = builder.items;
        this.express = builder.express;
        this.paymentMethod = builder.paymentMethod;
        this.deliveryAddress = builder.deliveryAddress;
        this.specialInstructions = builder.specialInstructions;
    }

    //필수것들만 final, 아닌것들은 final 뺴기
    public static class Builder {
        private final String orderId;
        private final String customerName;
        private List<String> items =new ArrayList<>();
        private boolean express;
        private String paymentMethod;
        private String deliveryAddress;
        private String specialInstructions;

        public Builder(String orderId, String customerName) {
            this.orderId = Objects.requireNonNull(orderId, "orderId cannot be null");
            this.customerName = Objects.requireNonNull(customerName, "customerName cannot be null");
        }

        public Builder items(List<String> items){
            this.items = new ArrayList<>(items);
            return this;
        }
        public Builder express(boolean express){
            this.express = express;
            return this;
        }

        public Builder paymentMethod(String paymentMethod){
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder specialInstructions(String specialInstructions){
            this.specialInstructions = specialInstructions;
            return this;
        }
       public  OrderNew build() {
            validateOrderData();
            return  new OrderNew(this);
       }

       public Builder deliveryAddress(String deliveryAddress){
        this.deliveryAddress = deliveryAddress;
        return this;
       }
       private void  validateOrderData() {
            if(items.isEmpty()) {
                throw new IllegalArgumentException("items cannot be empty");
            }
            if(deliveryAddress == null && !items.isEmpty()) {
                throw  new IllegalArgumentException("deliveryAddress cannot be null when items are not empty");
            }
       }
    }

    @Override
    public String toString() {
        return "OrderNew{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", items=" + items +
                ", express=" + express +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", specialInstructions='" + specialInstructions + '\'' +
                '}';
    }
}
