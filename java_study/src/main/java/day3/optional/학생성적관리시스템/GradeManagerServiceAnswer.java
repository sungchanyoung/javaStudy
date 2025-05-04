package day3.optional.학생성적관리시스템;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class GradeManagerServiceAnswer {
    private Map<String, Student> student = new ConcurrentHashMap<>();

    //1 학생 ID로 학생 조회하는 메서드 - null 일 수 있다
    public Optional<Student> findStudent(String studentId){
        return Optional.ofNullable(student.get(studentId));
    }

    //2 학생의 특정 과목 성적을 조회하는 메서드
    public Optional<Integer> getStudentGrade(String studentId, String subject){
        return findStudent(studentId)
                .flatMap(student1 -> student1.getGrade(subject));
    }

    //모든 과목에 성적 평균을 계산하는 메서드
    //map안에서 중괄호를 열면 return문이 있어야 한다
    //of는 절대 null일 될수 없다
    public Optional<Double> calculateAverageGrade(String studentId){
        return findStudent(studentId)
                .map(student1 -> {
                    Map<String, Integer> grades = student1.getGrades();
                    return grades.isEmpty() ?
                            Optional.<Double>empty() :
                            Optional.of(grades.values().stream()
                                    .mapToInt(Integer::intValue)
                                    .average()
                                    .orElse(0.0));
                })
                .orElse(Optional.empty());
    }

    //학생의 전공 과목 성적을 조회하는 메서드
    public String getMajorSubjectGrade(String studentId, String majorSubject){
        return findStudent(studentId)//optional
                .flatMap(Student::getMajor)//optional
                .map(major -> {
                    return findStudent(studentId)
                            .flatMap(student -> student.getGrade(majorSubject))
                            .map(grade -> String.format("%s전공 학생의 %s성적 :%d",major,majorSubject,grade))
                            .orElse(String.format("%s전공 학생이지만 %s 과목 성적이 없습니다"));
                })
                .orElse("학새을 찾을수 없거나 전공 정보가 없습니다");

    }

    //성적을 우수 확생 목고록을 반환하는 메서드
    public List<String> getTopStudents(String subject, int minGrade){
        return student.values().stream()
                .filter(student -> student.getGrade(subject)
                        .filter(grade ->grade >= minGrade )
                        .isPresent())
                .map(Student::getName)
                .toList();
    }

    //학생 성적 정보 요약
    public String getGradeSummary(String studentId){
        //ifPresentOrElse
        return findStudent(studentId)
                .map(student -> {
                    double average =calculateAverageGrade(studentId)
                            .orElse(0.0);

                    //전공 정보 포함
                    String majorInfo = student.getMajor()
                            .map(major -> "전공" + major)
                            .orElse("전공 미정");
                    return String.format("학생: %s, %s, 평균 성적: %.1f",
                            student.getName(), majorInfo, average);

                })
                .orElse("학생 정보가 없습니다");
    }
}
