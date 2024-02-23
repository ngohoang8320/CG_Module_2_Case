package services.productservice;

import controller.owner.OwnerHandlingProductController;
import entity.product.Product;
import repo.convertdata.product.ListProduct;
import repo.getsetdata.product.FormatProductData;
import repo.getsetdata.product.GetSetProduct;
import services.userservice.ShowProductList;
import view.NewPage;

import java.util.*;

public class RemoveProduct {
    private static int choseProduct;
    private int choice;
    private Scanner input = new Scanner(System.in);
    private GetSetProduct getSetProduct = new GetSetProduct();
    private List<Product> listProduct = (new ListProduct()).getProductList();

    public Map<Integer, Product> remove(Map<Integer, Product> noProductMap) {
        System.out.println("\u001B[3m\n(Choosing by enter the number in the No. column)\u001B[0m");
        System.out.print("What product would you like to remove: ");
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
                    remove(noProductMap);
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

        listProduct.remove(productToEdit);

        getSetProduct.clearData();
        for (Product prod : listProduct) {
            String entity = FormatProductData.formatted(prod.getId(),
                    prod.getName(),
                    prod.getOriginPrice(),
                    prod.getPromotionalPricing(),
                    prod.getQuantity());

            getSetProduct.setData(entity);
        }
        Set<Integer> listKey = noProductMap.keySet();
        for (int key : listKey) {
            if (key >= choseProduct) {
                noProductMap.put(key,
                        noProductMap.get(key + 1));
            }
        }
        noProductMap.remove(noProductMap.size());
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
