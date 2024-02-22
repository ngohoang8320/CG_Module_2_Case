package services.userservice;

import entity.user.CurrentUser;
import view.NewPage;

import java.util.Scanner;

public class GetInformation {
    private CurrentUser currentUser = CurrentUser.getInstance();

    public void getInfor() {
        NewPage.newPage();
        System.out.printf("%-10s\u001B[1m\u001B[4mYOUR INFORMATION:\u001B[0m\n",
                "");
        System.out.printf("\u001B[1m%-15s\u001B[0m%22s\n",
                "Username:",
                currentUser.getUserName());
        System.out.printf("\u001B[1m%-15s\u001B[0m%22s\n",
                "Password:",
                currentUser.getPassword());
        System.out.printf("\u001B[1m%-15s\u001B[0m%22s\n",
                "Phone number:",
                currentUser.getPhoneNumber());
        System.out.printf("\u001B[1m%-15s\u001B[0m%22s\n",
                "Email:",
                currentUser.getEmail());
        System.out.printf("\u001B[1m%-15s\u001B[0m%22s\n\n",
                "Role:",
                currentUser.getRole());

        System.out.println("Press Enter to go back to the previous page.");
        Scanner input = new Scanner(System.in);
        input.nextLine();
        NewPage.newPage();
    }
}
