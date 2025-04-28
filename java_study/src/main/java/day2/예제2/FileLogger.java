package day2.예제2;

public class FileLogger implements Logger{
    private final String fileLevel;
    private final LogLevel logLevel;


    public FileLogger(String filePath, LogLevel logLevel) {
        this.fileLevel = filePath;
        this.logLevel = logLevel;
    }
    @Override
    public void log(String message) {
    //파일 로그 작성 로직
    }

    @Override
    public void error(String message) {
        //파일 에러 작성 로직
    }

    @Override
    public LogLevel getLogLevel() {
        return logLevel;
    }
}
