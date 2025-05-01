package day3.로그분석기;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyzer {
    public static void main(String[] args) {
        List<LogEntry> logs = getLogs();
        //1.에러 로그 필터링 및 소스별 집계
        logs.stream()
                .filter(log -> log.getLevel().equals("ERROR"))
                .map(LogEntry::getSource);

        //2.시간대 별 로그 수 집계
        logs.stream()
                .filter(l -> l.getTimeStamp().isEqual(LocalDateTime.now().withHour(8)));

        //3.특정 기워드를 포함한 로그 검색
        logs.stream()
                .filter(l -> l.getMessage().contains("Null"));

        //4로그레벨 별 메시지 연결


        //정답
        //1 - 에러 로그 필터링 및 소스별 집계
        logs.stream()
                .filter(log -> "ERROR".equals(log.getLevel()))
                .collect(Collectors.groupingBy(
                        LogEntry::getSource,
                        Collectors.counting()
                ));

        //2 - 시간대 별 로그 수 집계
        Map<Integer,Long>logsByHours = logs.stream()
                .collect(Collectors.groupingBy(
                        log -> log.getTimeStamp().getHour(),
                        Collectors.counting()
                ));

        //3 - 특정 기워드를 포함한 로그 검색
        String keyword ="Exception";
        List<LogEntry> exception = logs.stream()
                .filter(log -> log.getMessage().contains(keyword))
                .sorted(Comparator.comparing(LogEntry::getTimeStamp))
                .toList();

        //4 -로그레벨 별 메시지 연결
        Map<String, String> logsBySource = logs.stream()
                .collect(Collectors.groupingBy(
                        LogEntry::getLevel,
                        Collectors.mapping(LogEntry::getMessage,
                                Collectors.joining("\n")
                        )
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
