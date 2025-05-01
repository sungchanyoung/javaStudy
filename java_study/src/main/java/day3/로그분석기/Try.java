package day3.로그분석기;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Try {
    public static void main(String[] args) {
        List<LogEntry> logs = getLogs();

        //1번 에러로그 필터링 및 소스별 집계
        //groupingBy :집계 함수
        //counting  갯수 세워주기
        logs.stream()
                .filter(log->"ERROR".equals(log.getLevel()))
                .collect(Collectors.groupingBy(
                        LogEntry::getSource,
                        Collectors.counting()
                ));

        //2번 시간대 별로 로그 갯수 세기
        Map<Integer,Long> logsByHours =  logs.stream()
                        .collect(Collectors.groupingBy(
                            log -> log.getTimeStamp().getHour(),
                                Collectors.counting()
                ));

        //3번 특정 키워드 포함 로그 검색
        //sorted :자연 순서로 정렬
        //Comparator.comparing : 비교해서 빠른 순서대로 셋팅해준다
        String keyWord = "Exception";
        List<LogEntry> exception = logs.stream()
                .filter(logEntry -> logEntry.getMessage().contains(keyWord))
                .sorted(Comparator.comparing(LogEntry::getTimeStamp))
                .toList();

        //로그별 메시지 연결
        //mapping : 스트림 요소에 특정 값을 추출한 다음 그값을 가지고 수집한다
        //joining :요소를 문자로 연결
        Map<String, String> logsBySource = logs.stream()
                .collect(Collectors.groupingBy(
                        LogEntry::getSource,
                        //getMassage만 뽑아서
                        Collectors.mapping(LogEntry::getMessage,
                                Collectors.joining("\n"))
                ));
    }
    private static List<LogEntry> getLogs(){
        return  List.of(
                new LogEntry(LocalDateTime.now(), "INFO", "Application started", "app"),
                new LogEntry(LocalDateTime.now(), "ERROR", "Division by zero", "app"),
                new LogEntry(LocalDateTime.now(), "ERROR", "Null pointer exception", "app"),
                new LogEntry(LocalDateTime.now(), "INFO", "Application stopped", "app")
        );
    }
}
