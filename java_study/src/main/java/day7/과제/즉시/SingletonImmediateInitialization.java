package day7.과제.즉시;


import day7.과제.더블체크.LoggerSingleton;
import lombok.Getter;
import lombok.Setter;

import java.io.PrintWriter;
import java.util.Date;

@Getter
@Setter
public class SingletonImmediateInitialization {
    //생성자가 무조건 private로 설정되어 있어야 한다.
    private static final SingletonImmediateInitialization INSTANCE = new SingletonImmediateInitialization();

    public enum LogLevel{
        INFO,
        DEBUG,
        WARNING,
        ERROR
    }

    private LogLevel minLogLevel = LogLevel.INFO;
    private String logFilePath;
    private PrintWriter logWriter;

    private SingletonImmediateInitialization(){
        try{
            initialize("log.txt", LogLevel.INFO);
        } catch (Exception e) {
            throw new RuntimeException("로그 파일 초기화 실패 "+ e.getMessage(),e);
        }
    }

    public void initialize(String logFilePath, LogLevel minLogLevel) throws Exception{
        this.logFilePath = logFilePath;
        this.minLogLevel = minLogLevel;
        this.logWriter = new PrintWriter(logFilePath);
    }

    public static SingletonImmediateInitialization getInstance() {
        return INSTANCE;
    }

    public void log(LogLevel logLevel, String message){
        if (logLevel.ordinal() < minLogLevel.ordinal()){
            return;
        }
        String timestamp = String.format("yyyy-MM-dd").formatted(new Date());
        String logEntry = String.format("[%s] [%s] %s%n", timestamp, logLevel, message);

        logWriter.println(logEntry);
        logWriter.flush();
        System.out.println(logEntry);
    }

    public void debug(String message) { log(LogLevel.DEBUG, message); }
    public void info(String message) { log(LogLevel.INFO, message); }
    public void warning(String message) { log(LogLevel.WARNING, message); }
    public void error(String message) { log(LogLevel.ERROR, message); }

    public void error(String message, Exception e) {
        log(LogLevel.ERROR, message + " - " + e.getMessage());
        e.printStackTrace(System.err);
    }

}
