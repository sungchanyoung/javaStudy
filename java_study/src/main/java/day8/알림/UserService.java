package day8.알림;

public class UserService {
    private final NotificationService notificationService;

    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void registeruser(String username){
        System.out.println("사용자 등록" + username);
        notificationService.sendNotification("회원가입 완료" + username);
    }
}
