package services.userservice.customer_account.cart;

import controller.customer.CartPageController;
import entity.product.Product;
import entity.user.CurrentUser;
import repo.convertdata.product.MapNoProduct;
import repo.getsetdata.product.GetSetCart;
import services.userservice.ShowProductList;
import view.NewPage;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Remove {
    private Scanner input = new Scanner(System.in);

    public void remove() {
        GetSetCart getSetCart = new GetSetCart();
        Map<String, List<Product>> mapUsernameCart = getSetCart.getData();

        CurrentUser currentUser = CurrentUser.getInstance();
        List<Product> listCart = mapUsernameCart.get(currentUser.getUserName());

        Map<Integer, Product> mapNoProduct = (new MapNoProduct()).getMap(listCart);

        System.out.println("\u001B[3m\n(Choosing by enter the number in the No. column)\u001B[0m");
        System.out.print("What product would you like to remove from your cart: ");
        try {
            int choice = input.nextInt();
            input.nextLine();
            if (mapNoProduct.get(choice) == null) {
                System.err.println("\nThere is no product at No. "
                                   + choice
                                   + "\nPlease choose the product base on the following table.");
                input.nextLine();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ix) {
                    throw new RuntimeException(ix);
                }
                NewPage.newPage();
                CartPageController.controller();
            } else {
                areYouSure(mapNoProduct.get(choice).getName());
                listCart.remove(mapNoProduct.get(choice));
                getSetCart.setData(mapUsernameCart);
                NewPage.newPage();
            }
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
                    NewPage.newPage();
                    ShowProductList.show(mapNoProduct);
                    remove();
                case 2:
                    NewPage.newPage();
                    ShowProductList.show(mapNoProduct);
                    CartPageController.controller();
                default:
                    System.out.println("Do not have this feature.");
            }
        }
    }

    private void areYouSure(String name) {
        System.out.println("\nWould you really want to remove \"" + name + "\" from the cart?\n1. Yes\n2. No");
        System.out.print("Your choice: ");
        String choose = input.nextLine();
        switch (choose) {
            case "1":
                break;
            case "2":
                CartPageController.controller();
            default:
                System.err.println("Just choose 1 (for Yes) or 2 (for No).");
                areYouSure(name);
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
