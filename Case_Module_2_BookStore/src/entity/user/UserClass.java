package entity.user;

import controller.customer.StoreController;
import controller.gate.GateController;
import controller.owner.OwnerController;
import regex.AccountRegex;
import services.userservice.sign.CountSignTime;
import view.NewPage;

import java.util.Scanner;

public class UserClass {
    private static Scanner input = new Scanner(System.in);

    private String userName;
    private String password;
    private String phoneNumber;
    private String email;
    private String role;
    private double money;

    private static void cancelInput() {
        CurrentUser currentUser = CurrentUser.getInstance();
        if (currentUser.getRole() == null) {
            GateController.gateController();
        } else if (currentUser.getRole().equals("Customer")) {
            StoreController.storeController();
        } else if (currentUser.getRole().equals("Owner")) {
            OwnerController.ownerController();
        }

    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName() {
        NewPage.newPage();
        System.out.println("\u001B[36mUSERNAME: \u001B[0m");
        System.err.println("Username must be at least 6 characters, include at least one uppercase letter and number!");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Enter your user name: ");
        userName = input.nextLine();
        if (!AccountRegex.checkUserName(userName)) {
            new CountSignTime();
            if (!CountSignTime.isAtLimitTimeInput()) {
                NewPage.newPage();
                setUserName();
            } else {
                NewPage.newPage();
                cancelInput();
            }
        } else {
            CountSignTime.resetCount();
        }
    }

    public void setPassword() {
        NewPage.newPage();
        System.out.println("\u001B[36mPASSWORD: \u001B[0m");
        System.err.println("Password must be at least 6 characters, include at least one uppercase letter, number and a special character!");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Enter your password: ");
        password = input.nextLine();
        if (!AccountRegex.checkPassword(password)) {
            new CountSignTime();
            if (!CountSignTime.isAtLimitTimeInput()) {
                NewPage.newPage();
                setPassword();
            } else {
                NewPage.newPage();
                cancelInput();
            }
        } else {
            CountSignTime.resetCount();
        }
    }

    public void setPhoneNumber() {
        NewPage.newPage();
        System.out.println("\u001B[36mPHONE NUMBER: \u001B[0m");
        System.err.println("Phone number have to follow this format 0x{8} or 0x{9}!");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Enter your phone number: ");
        phoneNumber = input.nextLine();
        if (!AccountRegex.checkPhoneNumber(phoneNumber)) {
            new CountSignTime();
            if (!CountSignTime.isAtLimitTimeInput()) {
                NewPage.newPage();
                setPhoneNumber();
            } else {
                NewPage.newPage();
                cancelInput();
            }
        } else {
            CountSignTime.resetCount();
        }
    }

    public void setEmail() {
        NewPage.newPage();
        System.out.println("\u001B[36mEMAIL: \u001B[0m");
        System.out.print("Enter your email: ");
        email = input.nextLine();
        if (!AccountRegex.checkEmail(email)) {
            new CountSignTime();
            if (!CountSignTime.isAtLimitTimeInput()) {
                System.err.println("The email you entered is not right!");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                NewPage.newPage();
                setEmail();
            } else {
                NewPage.newPage();
                cancelInput();
            }
        } else {
            CountSignTime.resetCount();
        }
    }
}
