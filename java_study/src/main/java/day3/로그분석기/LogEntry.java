package day3.로그분석기;

import lombok.*;

import java.time.LocalDateTime;

/*
    로그 항목을 나타내는 클래스
    타임스탬프 로그 레벨,
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogEntry {
    private LocalDateTime timeStamp;
    private String level; //info에 관한 것들
    private String message;
    private String source;
}
