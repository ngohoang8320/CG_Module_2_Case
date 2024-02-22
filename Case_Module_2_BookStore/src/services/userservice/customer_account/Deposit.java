package services.userservice.customer_account;

import controller.customer.StoreController;
import entity.user.CurrentUser;
import entity.user.Owner;
import entity.user.UserClass;
import repo.convertdata.user.MapUsernameUser;
import repo.getsetdata.user.FormatUserData;
import repo.getsetdata.user.GetSetAccount;
import services.userservice.Logging;
import services.userservice.owner_account.SetOwnerAccount;
import view.NewPage;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Deposit {
    private Scanner input = new Scanner(System.in);
    private CurrentUser currentUser = CurrentUser.getInstance();
    private Owner ownerRef = SetOwnerAccount.getInstance();
    private MapUsernameUser mapUsernameUser = new MapUsernameUser();
    private Map<String, UserClass> mapUser = mapUsernameUser.getMapUsernameUser();
    private UserClass userToEdit = mapUser.get(currentUser.getUserName());
    private UserClass owner = mapUser.get(ownerRef.getUserName());
    private GetSetAccount getSetAccount = new GetSetAccount();
    private Logging logging = new Logging();


    public void deposit() {
        NewPage.newPage();
        try {
            System.out.print("How much would you like to deposit: ");
            double deposit_money = input.nextDouble();
            if (deposit_money < 0) {
                throw new InputMismatchException();
            }
            input.nextLine();

            System.out.println();
            userToEdit.setMoney(userToEdit.getMoney() + deposit_money);

            NewPage.newPage();
            System.out.printf("\u001B[1m\u001B[32mNotice: \u001B[0mYou've just deposited "
                              + deposit_money
                              + " ($)\n\n");
            System.out.println("Press Enter to go back to the Store page.");
            input.nextLine();
            NewPage.newPage();

            writeToLog(deposit_money);
        } catch (InputMismatchException e) {
            input.nextLine();
            System.err.println("Wrong format!");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            int choice = wantToTryAgain();

            switch (choice) {
                case 1:
                    deposit();
                case 2:
                    NewPage.newPage();
                    StoreController.storeController();
                default:
                    System.out.println("Do not have this feature.");
            }
        }
        CurrentUser.getInstance().setMoney(userToEdit.getMoney());

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

    private void writeToLog(double depositMoney) {
        String cusMessage = "You're just deposit " + depositMoney + "($).";
        String cusFluctuation = "+" + depositMoney + "($)";
        logging.writeLog(userToEdit.getUserName(),
                cusMessage,
                cusFluctuation);
    }
}
