package services.userservice.sign;

import entity.user.UserClass;
import repo.convertdata.user.MapUsernameUser;

import java.util.Map;
import java.util.Set;

public class CheckPhoneNumber {
    public static boolean check(String phoneNumber) {
        MapUsernameUser mapUsernameUser = new MapUsernameUser();
        Map<String, UserClass> mapUser = mapUsernameUser.getMapUsernameUser();
        Set<String> userSet = mapUser.keySet();
        for (String user : userSet) {
            if ((mapUser.get(user).getPhoneNumber()).equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
}
