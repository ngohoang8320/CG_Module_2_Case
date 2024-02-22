package entity.user;

public class CurrentUser extends UserClass {
    private static CurrentUser currentUser = null;

    private CurrentUser() {
    }

    public static CurrentUser getInstance() {
        if (currentUser == null) {
            currentUser = new CurrentUser();
        }
        return currentUser;
    }

    public static void resetCurrentUser() {
        currentUser = null;
    }
}
