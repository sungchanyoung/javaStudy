package day3.optional.사용자프로필;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private String userId;
    private String name;
    private String email;
    private Address address;
}
