package day5.알림시스템;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EmailNotification implements NotificationStrategy{
//    SMTP 서버 주소
//    SMTP 서버 포트
//    발신자 이메일
//    발신자 비밀번호

    private String smtpServer;
    private int smtpPort;
    private String senderEmail;
    private String senderPassword;


    @Override
    public boolean sendNotification(String recipient, String subject, String content) {
        System.out.println("이메일 알림 전송 :");
        System.out.println("수신사:" + recipient);
        System.out.println("제목 :" + subject);
        System.out.println("내용"+ content);
        System.out.println("SMTP 서버 및 포트 : " + smtpServer + ":" + smtpPort);
        return true;
    }

}
