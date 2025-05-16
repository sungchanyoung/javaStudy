package day8.알림서비스;

public class SMSClient {
    public void sendSMS(String phoneNumber,String message){
        System.out.println("number "+ phoneNumber + " SMS sent: " + message);
    }
}
