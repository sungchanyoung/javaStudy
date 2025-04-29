package day3.optional.학생성적관리시스템;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
}
