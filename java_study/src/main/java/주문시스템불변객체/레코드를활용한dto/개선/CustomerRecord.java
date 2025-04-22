package 주문시스템불변객체.레코드를활용한dto.개선;

import java.util.Objects;

public record CustomerRecord(
        String name,
        String email,
        String address
) {
    public CustomerRecord {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(email, "email cannot be null");
        Objects.requireNonNull(address, "address cannot be null");

        if(!email.contains("@")){
            throw new IllegalArgumentException("email is not valid");
        }
    }
}
