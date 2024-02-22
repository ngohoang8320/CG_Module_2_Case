package services.userservice;

import entity.user.CurrentUser;
import view.NewPage;

import java.util.Scanner;

public class CheckWalletBalance {
    Scanner input = new Scanner(System.in);
    private CurrentUser currentUser = CurrentUser.getInstance();

    public void checkWallet() {
        NewPage.newPage();
        System.out.printf("%10s\u001B[1m\u001B[4mYOUR WALLET:\u001B[0m\n",
                "");
        System.out.printf("\u001B[32m%10s %.2f%s\u001B[0m\n\n",
                "",
                currentUser.getMoney(),
                " ($)");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Press Enter to go back to the previous page...");
        input.nextLine();
        NewPage.newPage();
    }
}
