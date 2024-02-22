package services.userservice;

import entity.user.CurrentUser;
import entity.user.UserClass;

public class SetCurrentUser {
    public static void setCurrentUser(UserClass user) {
        CurrentUser currentUser = CurrentUser.getInstance();
        currentUser.setUserName(user.getUserName());
        currentUser.setPassword(user.getPassword());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        currentUser.setEmail(user.getEmail());
        currentUser.setRole(user.getRole());
        currentUser.setMoney(user.getMoney());
    }
}
