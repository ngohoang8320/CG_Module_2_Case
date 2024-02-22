package services.userservice.customer_account.cart;

import controller.customer.CartPageController;
import entity.product.Product;
import entity.user.CurrentUser;
import repo.convertdata.product.ListProduct;
import repo.convertdata.product.MapNameProduct;
import repo.convertdata.product.MapNoProduct;
import repo.getsetdata.product.GetSetCart;
import services.userservice.ShowProductList;
import view.NewPage;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EditQuantity {
    private Scanner input = new Scanner(System.in);

    public void edit() {
        GetSetCart getSetCart = new GetSetCart();
        Map<String, List<Product>> mapUsernameCart = getSetCart.getData();

        CurrentUser currentUser = CurrentUser.getInstance();
        List<Product> listCart = mapUsernameCart.get(currentUser.getUserName());

        Map<Integer, Product> mapNoProduct = (new MapNoProduct()).getMap(listCart);

        System.out.println("\u001B[3m\n(Choosing by enter the number in the No. column)\u001B[0m");
        System.out.print("What product would you like to edit the quantity: ");
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
                CartPageController.controller();
            }

            int newQuantity = enterNewQuantity(choice,
                    mapNoProduct);

            mapNoProduct.get(choice).setQuantity(newQuantity);

            getSetCart.setData(mapUsernameCart);
            NewPage.newPage();
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
                    edit();
                case 2:
                    NewPage.newPage();
                    ShowProductList.show(mapNoProduct);
                    CartPageController.controller();
                default:
                    System.out.println("Do not have this feature.");
            }
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

    private int enterNewQuantity(int choice,
                                 Map<Integer, Product> mapNoProduct) {
        System.out.print("Enter new quantity: ");
        int newQuantity = input.nextInt();
        input.nextLine();

        List<Product> listProduct = (new ListProduct()).getProductList();
        Map<String, Product> mapNameProduct = (new MapNameProduct(listProduct)).getMapNameProduct(listProduct);

        if (newQuantity < 0 || newQuantity > mapNameProduct.get(mapNoProduct.get(choice).getName()).getQuantity()) {
            System.err.println("The quantity you entered is not right or greater than product's quantity.\nPlease try again!");
            input.nextLine();
            return enterNewQuantity(choice,
                    mapNoProduct);
        }

        return newQuantity;
    }
}