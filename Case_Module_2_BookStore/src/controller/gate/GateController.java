package controller.gate;

import services.userservice.owner_account.SetOwnerAccount;
import services.userservice.sign.SignIn;
import services.userservice.sign.SignUp;
import view.NewPage;
import view.gate.Gate;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GateController {
    private static Scanner input = new Scanner(System.in);
    private static Gate gate = Gate.getInstance();
    private static int choice;


    public static void gateController() {
        gate.showGatePage();
        SetOwnerAccount.getInstance();

        System.out.print("Your choice: ");
        try {
            choice = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("We do not have this feature.");
            try {
                Thread.sleep(200);
            } catch (InterruptedException i) {
                throw new RuntimeException(i);
            }
            System.out.println("Please choose features represented of numbers based on the menu.");
            input.nextLine();
            input.nextLine();
            NewPage.newPage();
            gateController();
        }

        switch (choice) {
            case 1:
                SignIn in = new SignIn();
                in.signIn();
                NewPage.newPage();
                gateController();
            case 2:
                SignUp up = new SignUp();
                up.signUp();
                NewPage.newPage();
                gateController();
            case 0:
                System.exit(0);
            default:
                System.err.println("We do not have this feature.");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Please choose features represented of numbers based on the menu.");
                input.nextLine();
                NewPage.newPage();
                gateController();
        }
    }
}