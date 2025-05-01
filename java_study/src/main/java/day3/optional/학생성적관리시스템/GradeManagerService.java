package day3.optional.학생성적관리시스템;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GradeManagerService {
    private Map<String, Student> student = new ConcurrentHashMap<>();
    /*
    학생 ID로 학생 조회하는 메서드
    null일수 있다

    학생 특정과목을 조회하는 메서드
    매개변수로 studnetId,과목명 (subject)
    return 성적을 담음 Optional 빈 Optional

    학생의 모든 과목성적 평균을 계산하는 메서드
    studentId값으로 찾아야함
    return 평균 성적은 담은 OPttional학생이 없거나 과목이 빈 optional

    학생의 전공 과목성적을 조회하는 메서드
    studentId,majorsubject
     */
    //학생 ID로 학생 조회하는 메서드
    public Optional<Student> findStudent(String studentId){
        if(studentId == null){
           return Optional.empty();
        }
        return Optional.ofNullable(student.get(studentId));
    }

    // 학생 특정과목을 조회하는 메서드
    public Optional<Student> findStudentWithSubject(String studentId, String subject){
        if(studentId == null || subject == null){
            return Optional.empty();
        }
        return findStudent(studentId)
                .filter(student -> student.getGrades().containsKey(subject));
    }

    //학생의 모든 과목 성적 평균을 계산하는 메서드
    public Optional<Double> findStudentGradeAVG(String studentId){
        if (studentId == null) return Optional.empty();
        return findStudent(studentId)
                .map(Student::getGrades)
                .filter(grades -> !grades.isEmpty())
                .map(grades->grades.values().stream()
                        .mapToInt(Integer::intValue)
                        .average()
                        .orElse(0.0));
    }

    //학생의 전공 과목성적을 조회하는 메서드
    public Map<String,Integer> findStudentMajorSubjectGrade(String studentId, String major){
        Optional<Student> id = findStudent(studentId);

        if (id.isEmpty()){
            throw new IllegalArgumentException("studentId is null");
        }
        Student student = id.get();
        Map<String,Integer> grades = student.getGrades();
        return grades.entrySet().stream()
                .filter(entry->entry.getKey().equals(major))
                .collect(Collectors.toMap
                        (Map.Entry::getKey,Map.Entry::getValue));
    }
}
