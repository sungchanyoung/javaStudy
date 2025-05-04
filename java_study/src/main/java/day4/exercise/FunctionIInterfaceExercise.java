package day4.exercise;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class FunctionIInterfaceExercise {
    //함수형 인터페이스 추상 메서드 단한개만 가지는 인터페이스 - 요리사 라고 생각 해보자 추상 메서드 -요리 기술
    //1 Predicate<T> 짝수인지 판별 - T 타입을 받아 boolean을 반환 -조건 검사
    public void exercise1() {
        Predicate<Integer> isEven = n -> n % 2 == 0;

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream()
                .filter(isEven)
                .toList();
        System.out.println("짝수: " + evenNumbers);
    }

    //Function <T,R> t 타입을 받아서 R 이라는 반환을 한다
    public void exercise2() {
        Function<String, Integer> getLength = String::length; //method reference -메소드 참조
        List<String> words = Arrays.asList("java", "Lamba", "Stream", "Functional Interface");
        List<Integer> lengths = words.stream()
                .map(getLength)
                .toList();
    }

    // 3. Consumer<T>- 아무것도 반환 하지 않음 -기본족을 forEach -T타입을 받아 아무것도 반환 하지 않음
    //문자열을 대문자로 변화 해서 출력
    public void exercise3() {
        Consumer<String> printUpperCase = s -> System.out.println(s.toUpperCase());

        List<String> words = Arrays.asList("java", "Lamba", "Stream", "Functional Interface");
        words.forEach(printUpperCase);
    }

    //4. supplier - 현재 시간을 문자열로 반환하는 서플라이어 만들기
    public void exercise4(){ //supplier은 입력값어 T타입의 결과만 반환 데이터 공급
       Supplier<String> getCurrentTime =
               () -> LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
       //값을 가지고 올떄
        System.out.println( getCurrentTime.get());
    }

    //BiFunction<T,U,R>
    //두 숫자를 받아서 최댓값을 반환하는 - T,U을 타입을 받아 R 타입을 반환
    //apply -값을 불러 내오고싶을때 사용한 메서드
    public void exercise5(){
        BiFunction<Integer,Integer,Integer> max = Math::max;
        System.out.println("최댓값(10,20)"+max.apply(10,20));
    }

    //메서드 참조
    public void exercise6(){
        List<String> names = Arrays.asList("Kim","Lee","Park","Choi","Jeong","Kang");

        names.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("정렬된 이름 : "+names);
    }

    //사용자 정의 interface 직접 만들어보는것
    @FunctionalInterface
    interface StringTransformer{
        String transform(String input);
    }

    public void exercise7(){
        StringTransformer transformer =input -> input.replaceAll("\\s","");

        //reverse
        StringTransformer reverse = input -> new StringBuilder(input).reverse().toString();

        String text = "hello world";
        System.out.println("공백제거 " + transformer.transform(text));
        System.out.println("문자열 뒤집기 "+ reverse.transform(text));
    }

    //여러 함수형 인터페이스 조합
    public void exercise8(){
       List<String> fruits = Arrays.asList("apple","banana","orange","kiwi");
       //1. 5글자 이상 필터링
        Predicate<String> isLengthGreater = s -> s.length() >=5;

        //단어를 대문자로 변환한 Functional Interface
        Function<String,String> toUpperCase = String ::toUpperCase;

        //결과를 출력하는 Consumer
        Consumer<String> printFruit = System.out::println;

        //모든 조홥 사용해서 출력
        fruits.stream()
                .filter(isLengthGreater)
                .map(toUpperCase)
                .forEach(printFruit);

    }

    //function 합성 해보기
    public void exercise9(){
        //정수를 제곱하는 Function
        Function<Integer,Integer> square = n -> n * n;

        //정수에 2를 더하는 function
        Function<Integer,Integer> addTwo = n -> n + 2;

        //합성 합수 생성
        //andThen 앞에것을 먼저 수행하고 뒤에것 수행
        //compose는 매개변수 먼저 실행후 그다음에 실행
        Function<Integer,Integer> addAndSquare = square.compose(addTwo);
        Function<Integer,Integer> squareAndAdd = square.andThen(addTwo);

        //테스트
        int num =5;
        System.out.println(addAndSquare.apply(num));
        System.out.println(squareAndAdd.apply(num));


    }

    public static void main(String[] args) {

        FunctionIInterfaceExercise exercise = new FunctionIInterfaceExercise();
//        System.out.println("1. Predicate<T>");
//        exercise.exercise1();
//        System.out.println("2. Function<T,R>");
//        exercise.exercise2();
//        System.out.println("3. Consumer<T>");
//        exercise.exercise3();
//        System.out.println("4. Supplier<T>");
//        exercise.exercise4();
//        System.out.println("5. BiFunction<T,U,R>");
//        exercise.exercise5();
        exercise.exercise6();
        exercise.exercise7();
        exercise.exercise8();
        exercise.exercise9();
    }
}