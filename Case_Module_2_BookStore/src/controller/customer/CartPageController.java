package controller.customer;

import services.userservice.customer_account.Payment;
import services.userservice.customer_account.ShowCart;
import services.userservice.customer_account.cart.EditQuantity;
import services.userservice.customer_account.cart.Remove;
import view.FeaturesHaveNotBeenDevYet;
import view.NewPage;
import view.customer.CartPage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CartPageController {
    private static Scanner input = new Scanner(System.in);
    private static CartPage cartPage = CartPage.getInstance();
    private static int choice;

    public static void controller() {
        NewPage.newPage();
        ShowCart showCart = new ShowCart();
        boolean isCartEmpty = showCart.show();

        if (isCartEmpty) {
            System.out.println("Press Enter to go back to the store page.");
            input.nextLine();
            NewPage.newPage();
            StoreController.storeController();
        } else {
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
                controller();
            }

            switch (choice) {
                case 1:
                    EditQuantity editQuantity = new EditQuantity();
                    editQuantity.edit();
                    controller();
                case 2:
                    Remove remove = new Remove();
                    remove.remove();
                    controller();
                case 3:
                    FeaturesHaveNotBeenDevYet.notice();
                    controller();
                case 4:
                    Payment payment = new Payment();
                    payment.pay();
                    StoreController.storeController();
                case 5:
                    NewPage.newPage();
                    StoreController.storeController();
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
                    controller();
            }
        }
    }
}
