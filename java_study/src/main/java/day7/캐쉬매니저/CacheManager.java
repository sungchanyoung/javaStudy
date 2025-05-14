package day7.캐쉬매니저;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 즉시 초기화  -> 방식
 * 장점
 * 1. 구현이 간단하다
 * 2. 클래스 로딩 시점에 인스턴스가 생성이 되므로 스레드가 안정하다
 * 3. getInstance()메서드가 추가 동기화 없이 빠르게 실행된다
 *
 * 단점
 * 1.어플리케이션 시작시 바로 생성이 되기 떄문에 만약에 쓰지 않은 ㄴ경우 메모리 낭비 가능성이 큼
 * 2. 초기화 과정에서 발생할수 있는 예외를 클라이언트가 처리할 방법이 없다
 *
 */
public class CacheManager {
    private static final CacheManager INSTANCE = new CacheManager();

    private final Map<String, Object> cache;

    private final long defaultExpirationTime;

    private CacheManager() {
        this.cache = new ConcurrentHashMap<>();
        this.defaultExpirationTime = 3600000;
    }

    public static CacheManager getInstance() {
        return INSTANCE;
    }

    public void put(String key, Object value) {
        put(key, value, defaultExpirationTime);
    }

    public void put(String key, Object value, long expirationTime){
        if (key == null){
            throw  new IllegalArgumentException("key is null");
        }
       CacheItem cacheItem = new CacheItem(value, System.currentTimeMillis() + expirationTime);
    }

    public Object get(String key){
        if(key == null){
            return null;
        }
        Object item = cache.get(key);
        if (item == null){
            return null;
        }

        if (item instanceof CacheItem){
            CacheItem cacheItem = (CacheItem) item;
            if (cacheItem.isExpired()){
                cache.remove(key);
            }
        }
        return item;
    }

    @Getter
    private static class CacheItem{
        private final Object value;
        private final long expirationTime;

        public CacheItem(Object value, long expirationTime) {
            this.value = value;
            this.expirationTime = expirationTime;
        }
        public boolean isExpired(){
            return System.currentTimeMillis() > expirationTime;
        }
    }
}
