package day3.optional.상품제고시스템;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String id;
    private String name;
    private int stock;
    private BigDecimal price;
    
}
