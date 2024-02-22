package services.productservice;

import controller.customer.StoreController;
import entity.product.Product;
import repo.getsetdata.GetSetData;
import repo.getsetdata.product.FormatProductData;
import repo.getsetdata.product.GetSetProduct;
import view.NewPage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddProduct {
    private Scanner input = new Scanner(System.in);
    private Product product = new Product();
    private GetSetData getSetProduct = new GetSetProduct();

    private static int chooseId() {
        int tempId = 1;
        while (CheckIdProduct.isExist(tempId)) {
            tempId += 1;
        }
        return tempId;
    }

    public void addProduct() {
        NewPage.newPage();
        product.setName();
        if (!CheckNameProduct.isExist(product.getName())) {
            int tempId = chooseId();
            product.setId(tempId);
            product.setOriginPrice();
            product.setPromotionalPricing();
            product.setQuantity();

            String entity = FormatProductData.formatted(product.getId(),
                    product.getName(),
                    product.getOriginPrice(),
                    product.getPromotionalPricing(),
                    product.getQuantity());

            getSetProduct.setData(entity);
        } else {
            System.err.println("This product's name has been used!");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Would you want to try again with another name?");
            System.out.println("1. Yes");
            System.out.println("2. No");

            int choice = tryAgain();

            input.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                case 2:
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
            }
        }
    }

    private int tryAgain() {
        try {
            int choice = input.nextInt();
            return choice;
        } catch (InputMismatchException i) {
            System.out.println("Just choose 1 for Yes or 2 for No");
            return tryAgain();
        }
    }
}
