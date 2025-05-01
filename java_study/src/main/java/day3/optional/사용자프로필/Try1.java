package day3.optional.사용자프로필;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Try1 {
    //안전성을 보장하는 hashMap
    private Map<String, UserProfile> userProfiles = new ConcurrentHashMap<>();

    //1번쨰 사용자ID러 사용자의 프로필을 조회하는 메서드
    public Optional<UserProfile> findUserId(String userId){
        //ofNullable => userId가 있을수 도 있고 없을수도 있다 그UserProfile안에 있는지 체크를 해야함
        return Optional.ofNullable(userProfiles.get(userId));
    }

    //2번 사용자 주소를 형식화된 문자열로 변환하는 메소드
    //orElse : UserProfile객체 안에 address가 없으면 다른 주소를 사용하겠다
    //기본값이 있으면 기본값으로 대처한다
    public String getDisplayAddress(String userId){
        return findUserId(userId)
                .map(UserProfile::getAddress)
                .map(address -> String.format("%s %s %s",
                        address.getCity(),
                        address.getStreet(),
                        address.getZipcode())
                ).orElse("주소없음");

    }

    //사용자 이메일을 반환하는 메서드
    //orElseThrow :UserProfile 안에 userId에 해당하는 email이 없다 에러를 던져라
    public String getEmail(String userId){
        return findUserId(userId)
                .map(UserProfile::getEmail)
                .filter(email -> email.contains("@"))
                .orElseThrow(() ->new IllegalArgumentException("No email found for userId: " + userId));
    }

    //사용자 이메일을 업데이트 하는 메서드
    public void updateEmail(String userId, String newEmail){
        UserProfile userEmail = userProfiles.get(userId);

        if(userEmail != null){
            userEmail.setEmail(newEmail);
        }
        else{
            throw new IllegalArgumentException("No user found for userId: " + userId);
        }
    }

    //ifPresent -> 만약 현재에 뜻 값일 있을때 만 메서드 실행한다
    public void updateUserEmail(String userId, String newEmail){
        findUserId(userId)
                .ifPresent(user->user.setUserId(newEmail));
        System.out.println("updateUserEmail: " + newEmail);
    }

}
