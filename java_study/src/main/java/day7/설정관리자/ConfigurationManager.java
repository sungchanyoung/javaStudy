package day7.설정관리자;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public enum ConfigurationManager {

    //유일한 상수
    INSTANCE;

    private final Properties properties;

    private final ConcurrentHashMap<String,String> cachedProperties;

    private static final String DEFAULT_CONFIG_FILE = "config.properties";
    private String configFilePath;

    ConfigurationManager(){
        this.properties = new Properties();
        this.cachedProperties = new ConcurrentHashMap<>();
        this.configFilePath = DEFAULT_CONFIG_FILE;
    }

    private void loadProperties(){
        try(InputStream input = getClass().getClassLoader()
                .getResourceAsStream(configFilePath)){
            if (input == null){
                System.err.println("config file not found");
                return;
            }

            properties.clear();
            cachedProperties.clear();

            properties.load(input);
            System.out.println("설정 파일 "+ configFilePath +" 로드 완료");

        } catch (IOException e) {
            throw new RuntimeException("설정 파일 로드 실패" + e.getMessage(), e);
        }
    }

    public void setProperties(String key, String value){
        if (key == null || value == null){
            throw new IllegalArgumentException("key or value is null");
        }
        properties.setProperty(key, value);
        cachedProperties.put(key, value);
    }

    public String getProperties(String key){
        String cacheValue = cachedProperties.get(key);
        if (cacheValue != null){
            return cacheValue;
        }
        String value = properties.getProperty(key);
        if (value != null){
            cachedProperties.put(key, value);
        }
        return value;
    }

}
