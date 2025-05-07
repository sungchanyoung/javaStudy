package day5.알림시스템;

public interface NotificationStrategy {
    boolean sendNotification(String recipient, String subject, String content);
    /*
    recipient ; 수신자 정보
    subject : 메시지 제목
    content : 메시지 내용
     */

}
