package 실무에서자주발생하는문제예시.계선된예시;

import java.util.Objects;
import java.util.Optional;

class OrderRefacor{
    OrderStatusRefactor status;
    CustomerRefactor customer;
    public OrderStatusRefactor getStatus() {
        return status;
    }
    public CustomerRefactor getCustomer() {
        return customer;
    }
}

enum OrderStatusRefactor{
    COMPLETED
}

class CustomerRefactor{
    String name;

    public String getName() {
        return name;
    }
}

public class OrderProcessor {
    public void process(OrderRefacor order) {
        Objects.requireNonNull(order.getStatus(), "status cannot be null");
        if(OrderStatusRefactor.COMPLETED.equals(order.getStatus())){
            
        }
        CustomerRefactor customer = Optional.ofNullable(order.getCustomer())
                .orElseThrow(() -> new IllegalArgumentException());
        
        String customerName = customer.getName();
    }
}
