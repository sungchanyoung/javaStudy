package day5.알림시스템;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PushNotification implements NotificationStrategy {
    private String appId;
    private String apiKey;
    private  String platform;

     /*
    앱 식별자
    앱 ID
    푸시 서비스 API 키
    앱 팰랫폼 (IOS, Android)
     */

    @Override
    public boolean sendNotification(String recipient, String subject, String content) {
        System.out.println("Push 알림 전송");
        System.out.println("appID : " + appId);
        System.out.println("platform : " + platform);
        System.out.println("수신사" + recipient);
        System.out.println("제목 : " + subject);
        System.out.println("내용" + content);
        return true;
    }


}
