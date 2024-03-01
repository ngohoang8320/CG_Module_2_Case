package services.userservice;

import entity.user.UserClass;
import repo.convertdata.user.MapUsernameUser;

import java.util.Map;
import java.util.Set;

public class CheckEmail {
    public static boolean check(String email) {
        MapUsernameUser mapUsernameUser = new MapUsernameUser();
        Map<String, UserClass> mapUser = mapUsernameUser.getMapUsernameUser();
        Set<String> userSet = mapUser.keySet();
        for (String user : userSet) {
            if ((mapUser.get(user).getEmail()).equals(email)) {
                return true;
            }
        }
        return false;
    }
}
