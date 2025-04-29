package day2.실습;

import java.util.Objects;

public class Person {
    //필수 필드 final
    private final String name;
    private final int age;

    //선택적 필드 -final 불변성 확보
    private final String address;
    private final String phoneNumber;
    private final String email;

    //age를 유효성 검사 0보다 미만 보다작으면 안되고 150초과 안됨
    //email간단한 유효성 검증  @가 들어가 있는지 null아니거나 비워져있지 않은지

    private Person(Builder builder) {
       this.name = builder.name;
       this.age = builder.age;
       this.address = builder.address;
       this.phoneNumber = builder.phoneNumber;
       this.email = builder.email;

    }
    public static class Builder{
        private final String name;
        private final int age;
        private  String address = "";
        private  String phoneNumber = "";
        private  String email = "";

        public Builder(String name, int age){
            this.name = Objects.requireNonNull(name, "name cannot be null");
            if ( age < 0 || age > 150){
                throw new IllegalArgumentException("age must be between 0 and 150");
            }
            this.age =  Objects.requireNonNull(age, "age cannot be null");
        }

        public Builder address(String address){
            this.address = address;
            return this;
        }

        public Builder email(String email){
           if(email != null && !email.isEmpty() && !email.contains("@")){
               throw new IllegalArgumentException("email is not valid");
           }
           this.email = email;
           return this;
        }

        public Builder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }
    }

}
