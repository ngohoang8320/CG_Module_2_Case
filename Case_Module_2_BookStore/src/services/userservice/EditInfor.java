package services.userservice;

import entity.user.CurrentUser;
import entity.user.UserClass;
import repo.convertdata.user.MapUsernameUser;
import repo.getsetdata.user.FormatUserData;
import repo.getsetdata.user.GetSetAccount;
import view.NewPage;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class EditInfor {
    private static int choice;
    private CurrentUser currentUser = CurrentUser.getInstance();
    private Scanner input = new Scanner(System.in);
    private MapUsernameUser mapUsernameUser = new MapUsernameUser();
    private Map<String, UserClass> mapUser = mapUsernameUser.getMapUsernameUser();

    private GetSetAccount getSetAccount = new GetSetAccount();

    public void edit() {
        NewPage.newPage();
        System.out.println("What would you like to edit?");
        System.out.println("1. Password");
        System.out.println("2. Phone number");
        System.out.println("3. Email");

        System.out.println("5. Back\n");

        UserClass userToEdit = mapUser.get(currentUser.getUserName());

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
            edit();
        }
        System.out.println();
        switch (choice) {
            case 1:
                userToEdit.setPassword();
                break;
            case 2:
                userToEdit.setPhoneNumber();
                break;
            case 3:
                userToEdit.setEmail();
                break;
            case 5:
                break;
            default:
                System.err.println("We do not have this feature.");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Please choose features represented of numbers based on the menu.");
                input.nextLine();
                edit();
        }
        SetCurrentUser.setCurrentUser(userToEdit);
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
}
