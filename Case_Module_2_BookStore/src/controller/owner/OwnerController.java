package controller.owner;

import controller.gate.GateController;
import entity.product.Product;
import repo.getsetdata.product.SearchProduct;
import services.productservice.AddProduct;
import services.userservice.*;
import services.userservice.owner_account.ManageOrder;
import services.userservice.owner_account.Withdraw;
import view.FeaturesHaveNotBeenDevYet;
import view.NewPage;
import view.owner.OwnerPage;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class OwnerController {
    private static Scanner input = new Scanner(System.in);
    private static OwnerPage ownerPage = OwnerPage.getInstance();
    private static int choice;

    public static void ownerController() {
        ownerPage.showOwnerPage();

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
            ownerController();
        }

        switch (choice) {
            case 1:
                GetInformation getInformation = new GetInformation();
                getInformation.getInfor();
                ownerController();
            case 2:
                EditInfor editInfor = new EditInfor();
                editInfor.edit();
                ownerController();
            case 3:
                CheckWalletBalance checkWalletBalance = new CheckWalletBalance();
                checkWalletBalance.checkWallet();
                ownerController();
            case 4:
                Withdraw wdraw = new Withdraw();
                wdraw.withdraw();
                ownerController();
            case 5:
                ShowLog showLog = new ShowLog();
                showLog.show();
                ownerController();
            case 6:
                DisplayAllProduct displayAllProduct = new DisplayAllProduct();
                Map<Integer, Product> allProductMap = displayAllProduct.display();
                OwnerHandlingProductController.controller(allProductMap);
            case 7:
                SearchProduct searchProduct = new SearchProduct();
                Map<Integer, Product> searchedProductMap = searchProduct.search();
                OwnerHandlingProductController.controller(searchedProductMap);
            case 8:
                AddProduct addProduct = new AddProduct();
                addProduct.addProduct();
                NewPage.newPage();
                ownerController();
            case 9:
                FeaturesHaveNotBeenDevYet.notice();
                NewPage.newPage();
                ownerController();
            case 15:
                ManageOrder manageOrder = new ManageOrder();
                manageOrder.show();
                ownerController();
            case 20:
                LogOut logOut = new LogOut();
                logOut.out();
                GateController.gateController();
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
                ownerController();
        }
    }
}
