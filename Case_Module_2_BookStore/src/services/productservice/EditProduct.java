package services.productservice;

import controller.owner.OwnerHandlingProductController;
import entity.product.Product;
import repo.convertdata.product.ListProduct;
import repo.getsetdata.product.FormatProductData;
import repo.getsetdata.product.GetSetProduct;
import services.userservice.ShowProductList;
import view.NewPage;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EditProduct {
    private static int choseProduct;
    private int choice;
    private Scanner input = new Scanner(System.in);
    private GetSetProduct getSetProduct = new GetSetProduct();
    private List<Product> listProduct = (new ListProduct()).getProductList();

    public Map<Integer, Product> edit(Map<Integer, Product> noProductMap) {
        System.out.println("\u001B[3m\n(Choosing by enter the number in the No. column)\u001B[0m");
        System.out.print("What product would you like to edit: ");
        try {
            choseProduct = input.nextInt();
            input.nextLine();
            if (noProductMap.get(choseProduct) == null) {
                System.err.println("\nThere is no product at No. "
                                   + choseProduct
                                   + "\nPlease choose the product base on the following table.");
                input.nextLine();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ix) {
                    throw new RuntimeException(ix);
                }
                NewPage.newPage();
                ShowProductList.show(noProductMap);
                OwnerHandlingProductController.controller(noProductMap);
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
                    ShowProductList.show(noProductMap);
                    edit(noProductMap);
                case 2:
                    NewPage.newPage();
                    ShowProductList.show(noProductMap);
                    OwnerHandlingProductController.controller(noProductMap);
                default:
                    System.out.println("Do not have this feature.");
            }
        }

        System.out.println();

        Product referenceProduct = noProductMap.get(choseProduct);
        Product productToEdit = null;
        for (Product prod : listProduct) {
            if (prod.getId() == referenceProduct.getId()) {
                productToEdit = prod;
                break;
            }
        }

        System.out.println("What would you like to edit?");
        System.out.println("1. Name");
        System.out.println("2. Origin price");
        System.out.println("3. Promotional pricing");
        System.out.println("4. Quantity");
        System.out.println("5. All");
        System.out.println("6. Back");

        System.out.println();
        System.out.print("Your choice: ");
        choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                System.out.println();
                productToEdit.setName();
                break;
            case 2:
                System.out.println();
                productToEdit.setOriginPrice();
                break;
            case 3:
                System.out.println();
                productToEdit.setPromotionalPricing();
                break;
            case 4:
                System.out.println();
                productToEdit.setQuantity();
                break;
            case 5:
                System.out.println();
                productToEdit.setName();
                productToEdit.setOriginPrice();
                productToEdit.setPromotionalPricing();
                productToEdit.setQuantity();
                break;
            case 6:
                break;
            default:
                System.err.println("We do not have this feature.");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Please choose features represented of numbers based on the menu.");
                input.nextLine();
                edit(noProductMap);
        }
        NewPage.newPage();
        getSetProduct.clearData();
        for (Product prod : listProduct) {
            String entity = FormatProductData.formatted(prod.getId(),
                    prod.getName(),
                    prod.getOriginPrice(),
                    prod.getPromotionalPricing(),
                    prod.getQuantity());

            getSetProduct.setData(entity);
        }
        noProductMap.put(choseProduct,
                productToEdit);
        ShowProductList.show(noProductMap);
        return noProductMap;
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
