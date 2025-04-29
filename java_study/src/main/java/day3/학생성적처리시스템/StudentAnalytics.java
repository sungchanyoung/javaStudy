package day3.학생성적처리시스템;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentAnalytics {
    public static void main(String[] args) {


        // 테스트를 위한 학생 목록 생성
        List<Student> students = Arrays.asList(
                new Student("John", 95),  // A 등급
                new Student("Jane", 85),  // B 등급
                new Student("Tom", 75),   // C 등급
                new Student("Mary", 65)   // D 등급
        );
        //평균점수 계산
        //stram api사영하셔서 모든 학생의 점수에 대한 평군을 계산 double로
        double studentAVG = students.stream()
                .mapToInt(Student::getScore)
                .average()
                .orElse(0.0);
        System.out.println(studentAVG);

        //2
        List<Student> studentScore = students.stream()
                .filter(s -> s.getScore() >= 80)
                .collect(Collectors.toList());
        System.out.println(studentScore);

        //3성적별 집계 함수-  groupingBy
       Map<String,Long> gradeCount = students.stream()
                                .collect(Collectors.groupingBy(Student::getGrade,
                                Collectors.counting()
                ));

      List<String> sortStudent = students.stream()
                                .map(Student::getName)
                                .sorted()
                                .toList();
    }

}
