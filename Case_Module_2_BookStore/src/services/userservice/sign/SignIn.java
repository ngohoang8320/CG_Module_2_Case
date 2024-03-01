package services.userservice.sign;

import controller.customer.StoreController;
import controller.owner.OwnerController;
import entity.user.CurrentUser;
import entity.user.UserClass;
import repo.convertdata.user.MapUsernameUser;
import repo.getsetdata.GetSetData;
import repo.getsetdata.user.GetSetAccount;
import services.userservice.CheckPassword;
import services.userservice.CheckUsername;
import services.userservice.SetCurrentUser;
import view.NewPage;

import java.util.Scanner;

public class SignIn {
    private UserClass user = new UserClass();
    private GetSetData account = new GetSetAccount();

    public void signIn() {
        user.setUserName();
        if (CheckUsername.check(user.getUserName())) {
            user.setPassword();
            if (CheckPassword.check(user.getPassword())) {
                MapUsernameUser mapUsernameUser = new MapUsernameUser();

                user = mapUsernameUser.getMapUsernameUser().get(user.getUserName());
                /**
                 * Add a class for set current user
                 */
                SetCurrentUser.setCurrentUser(user);
                if (CurrentUser.getInstance().getRole().equals("Customer")) {
                    NewPage.newPage();
                    StoreController.storeController();
                } else if (CurrentUser.getInstance().getRole().equals("Owner")) {
                    NewPage.newPage();
                    OwnerController.ownerController();
                }

            } else {
                /**
                 * Add counter to check if user try again more than 3 time and lock the sign in ability.
                 * You need to use data to check
                 */
                System.err.println("The password you typed is not correct.");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Press Enter to go back to Gate page and try again.");
                Scanner input = new Scanner(System.in);
                input.nextLine();
            }
        } else {
            System.err.println("Your account has not been registered.");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Press Enter to go back to Gate page...");
            Scanner input = new Scanner(System.in);
            input.nextLine();
        }
    }
}