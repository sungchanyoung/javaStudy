package day2;

public class User {
    private final String firstName; //필수
    private final String lastName; //필수
    private final int age; //선택
    private final String phone;
    private final String address;
    private final String email;

    //필수 매개변수만 받는 생성자
    public User(String firstName, String lastName) {
        this(firstName, lastName, 0, "", "", "");
    }

    //선택적 1 ++
    public User(String firstName, String lastName, int age) {
        this(firstName, lastName, age, "", "", "");
    }

    //선택적 2++
    public User(String firstName, String lastName, int age, String phone) {
        this(firstName, lastName, age, phone, "", "");
    }

    //선택적 3++
    public User(String firstName, String lastName, int age, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.email = "";
    }

    public User(String firstName, String lastName, int age, String phone, String address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

}