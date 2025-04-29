package day1;

import java.util.Objects;

public class userDto {

    private String userName;
    private String password;
    private String email;
    private int age;

    public void setUserName(String userName) {
        this.userName = Objects.requireNonNull(userName, "userName cannot be null");
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValid() {
        return age >= 0;
    }

}
