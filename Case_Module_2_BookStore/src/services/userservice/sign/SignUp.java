package services.userservice.sign;

import entity.user.Customer;
import entity.user.UserClass;
import repo.getsetdata.GetSetData;
import repo.getsetdata.user.FormatUserData;
import repo.getsetdata.user.GetSetAccount;
import services.userservice.CheckEmail;
import services.userservice.CheckUsername;
import util.ConfirmViaEmail;
import view.NewPage;

import java.util.Scanner;

public class SignUp {
    private UserClass customer = new Customer();
    private GetSetData account = new GetSetAccount();
    private Scanner input = new Scanner(System.in);

    public void signUp() {
        customer.setUserName();
        if (!CheckUsername.check(customer.getUserName())) {
            customer.setPassword();
            customer.setPhoneNumber();
            if (!CheckPhoneNumber.check(customer.getPhoneNumber())) {
                customer.setEmail();
                if (!CheckEmail.check(customer.getEmail())) {
                    if (ConfirmViaEmail.confirm(customer.getEmail())) {
                        String entity = FormatUserData.formatted(customer.getUserName(),
                                customer.getPassword(),
                                customer.getPhoneNumber(),
                                customer.getEmail(),
                                customer.getRole(),
                                customer.getMoney());
                        account.setData(entity);
                        NewPage.newPage();
                        System.out.printf("\u001B[32mYour account has been registered successfully!\u001B[0m\n");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Press Enter to go back to Gate page...");
                        input.nextLine();
                        NewPage.newPage();
                    } else {
                        System.err.println("\n\nThe confirm code you entered is not right.\nSign up again.");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Press Enter to go back to Gate page...");
                        input.nextLine();
                    }
                } else {
                    System.err.println("This email has been registered!");

                    System.out.println("Press Enter to go back to Gate page...");
                    Scanner input = new Scanner(System.in);
                    input.nextLine();
                }
            } else {
                System.err.println("This phone number has been registered!");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Press Enter to go back to Gate page...");
                Scanner input = new Scanner(System.in);
                input.nextLine();
            }
        } else {
            System.err.println("This username has been registered!");
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