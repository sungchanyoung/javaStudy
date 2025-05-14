package day7.로깅매니저;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class LogManager {
    public enum LogLevel{
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    private LogLevel currentLogLevel = LogLevel.INFO;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //로그 파일 경로
    private final String logFilePath;

    private PrintWriter logWriter;


    //LazyHolder 클래스 정적 내부 클래수 지연 초기화 구현
    private static class LazyHolder{
        private static final LogManager INSTANCE = new LogManager();
    }

    private LogManager(){
        this("application.log");
    }

    private LogManager(String logFilePath){
        this.logFilePath = logFilePath;
        try{
            this.logWriter = new PrintWriter(new FileWriter(logFilePath, true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log(LogLevel.INFO, "로그 매니저가 초기화 되었습니다.");
    }

    public static LogManager getInstance(){
        return LazyHolder.INSTANCE;
    }

    public void log(LogLevel logLevel, String message){
        //현재 설정된 로그 레벨보다 낮은 레벨은 무시
        if(logLevel.ordinal() < currentLogLevel.ordinal()){
            return;
        }

        //현재 로그 레벨이 설정된 로그 레벨보다 큰 경우 로그 레벨 설정
        if(logLevel.ordinal() > currentLogLevel.ordinal()){
            return;
        }

        String timestemp = LocalDateTime.now().format(dateTimeFormatter);

        String foematMessage = String.format("[%s] %s : %s", timestemp, logLevel, message);

        logWriter.println(foematMessage);
        logWriter.flush();
        System.out.println(foematMessage);
    }

    public void close(){
        if (logWriter != null) {
            log(LogLevel.INFO, "로그시스템.종료");
            logWriter.flush();
            logWriter.close();
            logWriter = null;
        }
    }

}
