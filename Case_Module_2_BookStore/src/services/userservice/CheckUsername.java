package services.userservice;

import entity.user.UserClass;
import repo.convertdata.user.MapUsernameUser;

import java.util.Map;
import java.util.Set;

public class CheckUsername {
    public static boolean check(String username) {
        MapUsernameUser mapUsernameUser = new MapUsernameUser();
        Map<String, UserClass> mapUser = mapUsernameUser.getMapUsernameUser();
        Set<String> userSet = mapUser.keySet();
        for (String user : userSet) {
            if (user.equals(username)) {
                return true;
            }
        }
        return false;
    }
}
