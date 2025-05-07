package day5.알림시스템;

import java.util.HashMap;
import java.util.Map;

public class NotificationManager {
    private NotificationStrategy defaultStrategy;
    private Map<String, NotificationStrategy> strategyMap = new HashMap<>();

    //생성자 생성
    public NotificationManager(NotificationStrategy defaultStrategy) {
        this.defaultStrategy = defaultStrategy;
    }

    public void setDefaultStrategy(NotificationStrategy defaultStrategy) {
        this.defaultStrategy = defaultStrategy;
    }

    //특정 타입의 알림 전략을 등록하는
    public void registerStrategy(String type, NotificationStrategy strategy){
        strategyMap.put(type, strategy);
    }

    //알림을 전송하는 메서드
    //getOrDefault :
    public boolean notify(String type, String recipient, String subject, String content){
        NotificationStrategy strategy = strategyMap.getOrDefault(type, defaultStrategy);
        return strategy.sendNotification(recipient, subject, content);
    }

    public boolean notify(String recipient, String subject, String content){
        return defaultStrategy.sendNotification(recipient, subject, content);
    }

    //allChannels
    public boolean notifyAllChannels(String recipient, String subject, String content){
        boolean result = true;
        for (NotificationStrategy strategy : strategyMap.values()) {
           result  = result && strategy.sendNotification(recipient,subject,content);
        }

        //기본 전략 - 중복 방지 생각해야한다
        result = result && defaultStrategy.sendNotification(recipient, subject, content);
        return result;

    }

}
