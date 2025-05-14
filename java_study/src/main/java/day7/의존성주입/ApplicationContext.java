package day7.의존성주입;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ApplicationContext {
    private final Map<Class<?>, Object> singletons = new HashMap<>();
    private final Map<Class<?>, Supplier<?>> factories = new HashMap<>();

    private static class SingletonHolder{
        private static final ApplicationContext INSTANCE = new ApplicationContext();

    }

    private ApplicationContext(){
        //빈등록
        singletons.put(ApplicationContext.class, this);

    }

    public static ApplicationContext getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public <T> void register(Class<T> type, T instance){
        if(type == null || instance == null){
            throw new IllegalArgumentException("타입과 인스턴스는 null이 될수 없습니다");
        }
        singletons.put(type, instance);
    }

    public <T> void registerFactory(Class<T> type, Supplier<T> factory){
        if(type == null || factory == null){
            throw new IllegalArgumentException("타입과 생성자는 null이 될수 없습니다");
        }
        factories.put(type, factory);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> type){
        Object instance = singletons.get(type);

        if (instance == null){
            @SuppressWarnings("unchecked")
            Supplier<T> factory = (Supplier<T>) factories.get(type);

            if (factory != null){
                instance = factory.get();
                singletons.put(type, instance);
            }

        }
        return (T) instance;
    }
}
