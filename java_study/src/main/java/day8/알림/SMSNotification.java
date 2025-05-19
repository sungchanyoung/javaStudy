package day8.알림;

public class SMSNotification implements NotificationService{
    private final SMSClient smsClient;

    public SMSNotification(SMSClient smsClient){
        this.smsClient = smsClient;
    }
    @Override
    public void sendNotification(String message) {
        smsClient.sendSMS("zkzk7290@NAVER.COM",message);
    }

}
