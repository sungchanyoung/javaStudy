package day7.로깅매니저;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HolderLogManager {
    public enum LogLevel{
        DEDUG,INFO,WARNING,ERROR;

        @Override
        public String toString() {
            return name();
        }
    }

    //현재 로그 레벨 -> 기본 값은Info
    private LogLevel currentLogLevel = LogLevel.INFO;

    //날짜와 /시간 포멧터
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss.SSS");

    //logfile 경로
    private final String logFilePath ;

    //로그 작성기
    private PrintWriter logWriter;

    //LazyHolder클래스 > 지연 초기화 구현
    private static class LazyHolder{
        private static final HolderLogManager INSTANCE = new HolderLogManager();

    }

    private HolderLogManager(){
        this("application.log");
    }

    private HolderLogManager(String logFilePath){
        this.logFilePath = logFilePath;
        try{
            this.logWriter = new PrintWriter(new FileWriter(logFilePath,true));
        } catch (IOException e) {
            throw new RuntimeException("로그 파일 열 수 없습니다 "+e.getMessage(),e);
        }
        log(LogLevel.INFO ,"로깅 시스탬 초기화 완료");
    }

    public static HolderLogManager getInstance(){
        return LazyHolder.INSTANCE;
    }

    public void log (LogLevel level, String message){
        if (level.ordinal() < currentLogLevel.ordinal()){
            return;
        }

        String timestamp = LocalDateTime.now().format(dateTimeFormatter);
        String formatMessage = String.format("[%s] [%s] %s",timestamp, level, message);

        logWriter.println(formatMessage);
        logWriter.flush();
        System.out.println(formatMessage);
    }

    public void close(){
        if (logWriter != null){
            log(LogLevel.INFO,"로깅 시스탬 종료");
            logWriter.flush();
            logWriter.close();
            logWriter = null;
        }
    }


}
