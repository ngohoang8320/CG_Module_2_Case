package services.userservice.owner_account;

import controller.owner.OwnerController;
import entity.user.CurrentUser;
import entity.user.Owner;
import entity.user.UserClass;
import exception.DoNotHaveEnoughMoney;
import repo.convertdata.user.MapUsernameUser;
import repo.getsetdata.user.FormatUserData;
import repo.getsetdata.user.GetSetAccount;
import services.userservice.Logging;
import view.NewPage;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Withdraw {
    private Scanner input = new Scanner(System.in);
    private CurrentUser currentUser = CurrentUser.getInstance();
    private Owner ownerRef = SetOwnerAccount.getInstance();
    private MapUsernameUser mapUsernameUser = new MapUsernameUser();
    private Map<String, UserClass> mapUser = mapUsernameUser.getMapUsernameUser();
    private UserClass userToEdit = mapUser.get(currentUser.getUserName());
    private UserClass owner = mapUser.get(ownerRef.getUserName());

    private GetSetAccount getSetAccount = new GetSetAccount();
    private Logging logging = new Logging();


    public void withdraw() {
        NewPage.newPage();
        try {
            System.out.print("How much would you like to withdraw: ");
            double withdraw_money = input.nextDouble();
            if (withdraw_money < 0) {
                throw new InputMismatchException();
            }
            input.nextLine();
            try {
                double leftMoney = userToEdit.getMoney();
                double tempLeftMoney = userToEdit.getMoney() - withdraw_money;
                if (tempLeftMoney < 0) {
                    throw new exception.DoNotHaveEnoughMoney("Do not have enough money.");
                }
                leftMoney = tempLeftMoney;
                userToEdit.setMoney(leftMoney);

                writeToLog(withdraw_money);

                NewPage.newPage();
                System.out.printf("\u001B[1m\u001B[32mNotice: \u001B[0mYou've just withdraw "
                                  + withdraw_money
                                  + " ($)\n\n");
                System.out.println("Press Enter to go back to the Store page.");
                input.nextLine();
                NewPage.newPage();
            } catch (DoNotHaveEnoughMoney m) {
                System.err.println("You do not have enough money to withdraw.");
                confirmTryAgain();
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            System.err.println("Wrong format!");
            confirmTryAgain();
        }
        currentUser.setMoney(userToEdit.getMoney());

        Set<String> listAccount = mapUser.keySet();
        getSetAccount.clearData();
        for (String account : listAccount) {
            UserClass acc = mapUser.get(account);
            String entity = FormatUserData.formatted(acc.getUserName(),
                    acc.getPassword(),
                    acc.getPhoneNumber(),
                    acc.getEmail(),
                    acc.getRole(),
                    acc.getMoney());

            getSetAccount.setData(entity);
        }
    }

    private void writeToLog(double withdrawMoney) {
        String ownMessage = "You're just withdraw " + withdrawMoney + "($).";
        String ownFluctuation = "-" + withdrawMoney + "($)";
        logging.writeLog(owner.getUserName(),
                ownMessage,
                ownFluctuation);
    }

    private void confirmTryAgain() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        int choice = wantToTryAgain();
        switch (choice) {
            case 1:
                withdraw();
            case 2:
                NewPage.newPage();
                OwnerController.ownerController();
            default:
                System.out.println("Do not have this feature.");
        }
    }

    private int wantToTryAgain() {
        int choiceTry;
        System.out.println("Would you like to try again?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println();
        System.out.print("Your choice: ");
        try {
            choiceTry = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException ex) {
            input.nextLine();
            System.err.println("Just choose 1 (for Yes) or 2 (for No).");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ix) {
                throw new RuntimeException(ix);
            }
            return wantToTryAgain();
        }
        return choiceTry;
    }
}