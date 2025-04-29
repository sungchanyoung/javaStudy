package day3.optional.사용자프로필;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserProfileService {
    //안전성을 보장하는 hashMap
    private Map<String, UserProfile> userProfiles = new ConcurrentHashMap<>();

    //1번째로 사용자ID로 사용자의 프로필을 조회하는 메소드
    public Optional<UserProfile> findUserId(String userId){
        return Optional.ofNullable(userProfiles.get(userId));
    }

    //사용자의 주소를 형식화 된 문자열로 변환하는 메소드
    public String getDisplayAddress(String userId){
        return findUserId(userId) //사용자 조회
                .map(UserProfile::getAddress)//주소 객체를 추출
                .map(address -> String.format("%s %s %s", //주소 포맷팅
                        address.getStreet(),
                        address.getCity(),
                        address.getZipcode())
                )
                .orElse("주소 전부 없음");//주소가 업을경우 기본값 제공
    }
    /*
    사용자 이메일을 반환하는 메서드
    userId-매개변수
    return 사용자 이메일
    orElse
    * */
    public String getEmail(String userId){
        return findUserId(userId)
                .map(UserProfile::getEmail)
                .filter(email -> email.contains("@"))
                .orElseThrow(() ->new IllegalArgumentException("No email found for userId: " + userId));
    }
    //사용자 이메이일 업데이트 하는 메서드
    //사용자가 있어야 이메일을 업데이트할수 있다
    //userId String newEmail
    //return void

    public void updateEmail(String userId, String newEmail){
        UserProfile userEmail = userProfiles.get(userId);
        if(userEmail != null){
            userEmail.setEmail(newEmail);
        }
        else{
            throw new IllegalArgumentException("No user found for userId: " + userId);
        }
    }

    public void updateUserEmail(String userId, String newEmail){
        findUserId(userId)
                .ifPresent(user -> user.setEmail(newEmail));
        System.out.println("updateUserEmail: " + newEmail);

    }
}
