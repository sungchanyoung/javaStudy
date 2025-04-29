package day3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//기본 적인 스트림 생산
public class StreamExample1 {
    public static void main(String[] args) {
        //리스트로 스트림 생성
        List<String> name = Arrays.asList("Kim","Lee","Park","Choi","Jeong","Kang");

        System.out.println("==원본 데이터");
        System.out.println(name);
        //필터링 ->K로 시작하는 이름만 선택
        List<String>Kname = name.stream()
                .filter(n -> n.startsWith("K"))
                .toList();

        System.out.println("\n== K로 시작하는 이름 ==");
        System.out.println(Kname);

        //변환 map
        List<String> upperName = name.stream()
                .map(String::toUpperCase)
                .toList();

        //정렬 sorted -upperName
        List<String>names = name.stream()
                                .sorted()
                                .toList();

        //리스트 합치기 모든이름을 쉼표로 구분해서 하나의 문자열로 만들어 보는것
        String collectionName = name.stream()
                .collect(Collectors.joining(","));
//        String.join(",",name);
        //이 두가지 방법이 있다

        //형변환  -average
       double averageLength = name.stream()
                .mapToInt(String::length)
                .average()
               .orElse(0.0);
    }
}
