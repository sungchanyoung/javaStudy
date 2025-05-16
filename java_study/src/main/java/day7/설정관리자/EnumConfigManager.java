package day7.설정관리자;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * enum은 상수로
 * 안전하게 singleton 패턴을 구현할 수 있다.
 *enum 생성자는 기본적으로 private로 만들어진다.
 */
public enum EnumConfigManager {
    INSTANCE;

    private final Properties properties;
    private final ConcurrentHashMap<String,String> cachedProperties;

    private static final String DEFAULT_CONFIG_FILE = "config.properties";
    private String configFilePath;

    EnumConfigManager(){
        this.properties = new Properties();
        this.cachedProperties = new ConcurrentHashMap<>();
        this.configFilePath = DEFAULT_CONFIG_FILE;
        loadProperties();
    }

    /**
     * try(InputStream input = getClass().getClassLoader()
     *                 .getResourceAsStream(configFilePath)){
     * 현재 객체의 클래스 로더를 가져옵니다 (현재 객체의 클래스 로더는 enum 객체의 클래스 로더)
     * 클래스패스 기준 파일을 InputStream으로 읽음
     * try-with-resources: 자원 자동 해제
     */
    private void loadProperties(){
        try(InputStream input = getClass().getClassLoader()
                .getResourceAsStream(configFilePath)){

            if (input == null){
                System.err.println("config file not found" + configFilePath  );
                return;
            }

            properties.clear();
            cachedProperties.clear();
            properties.load(input);
            System.out.println("설정 파일 "+ configFilePath +" 로드 완료");
        } catch (IOException e) {
            throw new RuntimeException("설정 파일 로드 실패" + e.getMessage(),e);
        }
    }
}
