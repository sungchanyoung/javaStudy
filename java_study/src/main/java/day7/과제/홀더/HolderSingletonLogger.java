package day7.과제.홀더;

import day7.과제.basecode.LogLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class HolderSingletonLogger {
    public enum LogLevel{
        DEBUG,
        INFO,
        WARNING,
        ERROR;

        @Override
        public String toString() {
            return name();
        }
    }
    private String logFilePath;
    private LogLevel minLogLevel = LogLevel.INFO;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private PrintWriter logWriter;

    private static class LazyHolder{
        private static final HolderSingletonLogger INSTANCE = new HolderSingletonLogger();
    }

    public static HolderSingletonLogger getInstance(){
        return LazyHolder.INSTANCE;
    }

    private HolderSingletonLogger(){
        this("application.log");
    }

    private HolderSingletonLogger(String logFilePath){
        this.logFilePath = logFilePath;
        try{
            this.logWriter = new PrintWriter(new FileWriter(logFilePath,true));
        } catch (IOException e) {
            throw new RuntimeException("로그 파일을 열수 없습니다 " + e.getMessage(), e);
        }
    }

    public void log(LogLevel logLevel, String message){
        if (logLevel.ordinal() >= minLogLevel.ordinal()){
            String timestamp = LocalDateTime.now().format(dateTimeFormatter);
            String logEntry = String.format("[%s] [%s] %s%n", timestamp, logLevel, message);
            logWriter.print(logEntry);
        }
    }

    public void initialize(String logFilePath, LogLevel minLogLevel) throws Exception{
        this.logFilePath = logFilePath;
        this.minLogLevel = minLogLevel;
        this.logWriter = new PrintWriter(logFilePath);
    }

    public void debug(String message) { log(HolderSingletonLogger.LogLevel.DEBUG, message); }

    public void info(String message) { log(HolderSingletonLogger.LogLevel.INFO, message); }

    public void warning(String message) { log(HolderSingletonLogger.LogLevel.WARNING, message); }

    public void error(String message) { log(HolderSingletonLogger.LogLevel.ERROR, message); }

    public void error(String message, Exception e) {
        log(LogLevel.ERROR, message + " - " + e.getMessage());
        e.printStackTrace(System.err);
    }

    public void close(){
        if (logWriter != null){
            log(HolderSingletonLogger.LogLevel.INFO,"로깅 시스탬 종료");
            logWriter.flush();
            logWriter.close();
            logWriter = null;
        }
    }
}
