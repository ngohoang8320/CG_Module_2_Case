package repo.convertdata.user;

import entity.user.UserClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapPasswordUser {
    private Map<String, UserClass> mapPasswordUser = new HashMap<>();

    public MapPasswordUser() {
        setMapPasswordUser();
    }

    public Map<String, UserClass> getMapPasswordUser() {
        return mapPasswordUser;
    }

    public void setMapPasswordUser() {
        List<UserClass> accountList = (new ListAccount()).getAccountList();
        for (UserClass acc : accountList) {
            String password = acc.getPassword();
            mapPasswordUser.put(password, acc);
        }
    }
}
