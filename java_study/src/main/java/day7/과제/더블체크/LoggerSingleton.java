package day7.과제.더블체크;

import day7.과제.basecode.LogLevel;
import day7.과제.basecode.Logger;
import day7.데이터베이스관리자.DataBaseConnectionDoubleCheck;
import lombok.Getter;
import lombok.Setter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

@Getter
@Setter
public class LoggerSingleton {


    public enum LogLevel{
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }
    private String logFilePath;
    private LogLevel minLogLevel;
    private PrintWriter logWriter;

    private static volatile LoggerSingleton instance;

    private LoggerSingleton(){
        this.logFilePath = "log.txt";
        this.minLogLevel = LogLevel.INFO;
    }

    public void initialize(String logFilePath, LogLevel minLogLevel) throws SQLException {
        this.logFilePath = logFilePath;
        this.minLogLevel = minLogLevel;
        try{
            this.logWriter = new PrintWriter(new FileWriter(logFilePath, true));
        } catch (IOException e) {
            throw new RuntimeException("로그 파일 초기화 실패 " + e.getMessage(),e);
        }
    }

    public static LoggerSingleton getInstance(){
        if(instance == null){
            synchronized (LoggerSingleton.class){
                if(instance == null){
                    instance = new LoggerSingleton();
                }
            }
        }
        return instance;
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

    public void setMinLogLevel(LogLevel level) {
        this.minLogLevel = level;
    }

    public LogLevel getMinLogLevel() {
        return this.minLogLevel;
    }

    public String getLogFilePath() {
        return this.logFilePath;
    }

    // (선택) 인스턴스 초기화용
    public static void reset() {
        synchronized (LoggerSingleton.class){
            if (instance != null) {
                try{
                    instance.closeConnection();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
                instance = null;
            }
        }
    }

    private void closeConnection() throws SQLException{
        if(logWriter != null){
            logWriter.close();
            logWriter = null;
        }
    }


}
