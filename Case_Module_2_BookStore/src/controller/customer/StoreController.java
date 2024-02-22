package controller.customer;

import entity.product.Product;
import repo.getsetdata.product.SearchProduct;
import services.userservice.*;
import services.userservice.customer_account.Deposit;
import view.NewPage;
import view.customer.StorePage;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class StoreController {
    private static Scanner input = new Scanner(System.in);
    private static StorePage storePage = StorePage.getInstance();
    private static int choice;

    public static void storeController() {
        storePage.showStorePage();

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
            storeController();
        }

        switch (choice) {
            case 1:
                GetInformation getInformation = new GetInformation();
                getInformation.getInfor();
                storeController();
            case 2:
                EditInfor editInfor = new EditInfor();
                editInfor.edit();
                storeController();
            case 3:
                CheckWalletBalance checkWalletBalance = new CheckWalletBalance();
                checkWalletBalance.checkWallet();
                storeController();
            case 4:
                Deposit depos = new Deposit();
                depos.deposit();
                storeController();
            case 5:
                ShowLog showLog = new ShowLog();
                showLog.show();
                storeController();
            case 6:
                DisplayAllProduct displayAllProduct = new DisplayAllProduct();
                Map<Integer, Product> allProductMap = displayAllProduct.display();
                CustomerHandlingProductController.controller(allProductMap);
            case 7:
                SearchProduct searchProduct = new SearchProduct();
                Map<Integer, Product> searchedProductMap = searchProduct.search();
                CustomerHandlingProductController.controller(searchedProductMap);
            case 8:
                CartPageController.controller();
                storeController();
            case 9:
                LogOut logOut = new LogOut();
                logOut.out();
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
                storeController();
        }
    }
}
