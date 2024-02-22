package services.userservice.sign;

import entity.user.Customer;
import entity.user.UserClass;
import repo.getsetdata.GetSetData;
import repo.getsetdata.user.FormatUserData;
import repo.getsetdata.user.GetSetAccount;
import services.userservice.CheckUsername;
import view.NewPage;

import java.util.Scanner;

public class SignUp {
    private UserClass customer = new Customer();
    private GetSetData account = new GetSetAccount();

    public void signUp() {
        NewPage.newPage();
        customer.setUserName();
        if (!CheckUsername.check(customer.getUserName())) {
            customer.setPassword();
            customer.setPhoneNumber();
            customer.setEmail();
            String entity = FormatUserData.formatted(customer.getUserName(),
                    customer.getPassword(),
                    customer.getPhoneNumber(),
                    customer.getEmail(),
                    customer.getRole(),
                    customer.getMoney());
            account.setData(entity);
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