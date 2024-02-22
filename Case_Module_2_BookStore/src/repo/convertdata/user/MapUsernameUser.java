package repo.convertdata.user;

import entity.user.UserClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUsernameUser {
    private Map<String, UserClass> mapUsernameUser = new HashMap<>();

    public MapUsernameUser() {
        setMapUsernameUser();
    }

    public Map<String, UserClass> getMapUsernameUser() {
        return mapUsernameUser;
    }

    public void setMapUsernameUser() {
        List<UserClass> accountList = (new ListAccount()).getAccountList();
        for (UserClass acc : accountList) {
            String username = acc.getUserName();
            mapUsernameUser.put(username, acc);
        }
    }
}
