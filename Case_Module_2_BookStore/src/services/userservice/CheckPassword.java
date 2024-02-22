package services.userservice;

import entity.user.UserClass;
import repo.convertdata.user.MapPasswordUser;

import java.util.Map;
import java.util.Set;

public class CheckPassword {
    public static boolean check(String password) {
        MapPasswordUser mapPasswordUser = new MapPasswordUser();
        Map<String, UserClass> mapUser = mapPasswordUser.getMapPasswordUser();
        Set<String> userSet = mapUser.keySet();
        for (String user : userSet) {
            if (user.equals(password)) {
                return true;
            }
        }
        return false;
    }
}
